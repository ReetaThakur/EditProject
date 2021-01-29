package com.xyz.saveandreadafile;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FetchAndSaveService extends Service implements LooperReadyListener {

    private String dataToWrite;
    private FileWriteReadThread handlerThread;

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            try {
                File directory = new File(getExternalFilesDir(null) + File.separator + "randomDirectory");
                if (!directory.exists()) {
                    directory.mkdir();
                }

                File newFile = new File(directory, "lloyd.txt");
                if (!newFile.exists()) {

                    newFile.createNewFile();

                }

                // Write to file


                FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.append(dataToWrite);
                outputStreamWriter.close();
                //Read the data

                FileInputStream inputStream = new FileInputStream(newFile);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                StringBuffer stringBuffer = new StringBuffer();
                while (data != -1) {
                    char ch = (char) data;
                    stringBuffer.append(ch);
                    data = inputStreamReader.read();
                }

                // Update the activity
                Intent intent = new Intent("com.lloyd.fileFetch");
                intent.putExtra("data", stringBuffer.toString());
                sendBroadcast(intent);
                stopSelf();
            } catch (Exception e) {
            }
        }

    };

    public FetchAndSaveService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotification();
        } else {
            startForeground(2, new Notification());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dataToWrite = intent.getStringExtra("data");
        handlerThread = new FileWriteReadThread("Async", this);
        handlerThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotification() {

        String channelID = "Lloyd";
        String channelName = "FetchAndSaveService";
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_NONE);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID);

        Notification notification = builder
                .setContentTitle("File read and save")
                .setContentText("File is being written and read")
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .build();

        startForeground(1, notification);

    }

    @Override
    public void onLooperReady() {
        handlerThread.addTaskToQueue(task);
    }
}