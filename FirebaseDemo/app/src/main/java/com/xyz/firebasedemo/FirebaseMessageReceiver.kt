package com.xyz.firebasedemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * This class is triggered whenever a new FCM notification comes and onMessageReceived method is called
 */
class FirebaseMessageReceiver : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null) {
            showNotification(
                remoteMessage.notification?.title!!,
                remoteMessage.notification?.body!!
            )
        }
    }

    /**
     * This method is used to create a custom notification layout
     */
    private fun getCustomDesign(
        title: String,
        message: String
    ): RemoteViews? {
        val remoteViews = RemoteViews(
            applicationContext.packageName,
            R.layout.custom_notification
        )
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.masai_logo
        )
        return remoteViews
    }

    // Method to display the notifications
    private fun showNotification(
        title: String,
        message: String
    ) {
        // Pass the intent to switch to the MainActivity
        val intent = Intent(this, SignInActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


        var builder = NotificationCompat.Builder(applicationContext, channel_id)
            .setSmallIcon(R.drawable.masai_logo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        builder = builder.setContent(getCustomDesign(title, message))

        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channel_id, "web_app", NotificationManager.IMPORTANCE_HIGH)

            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("debug: token  $token")
    }
}