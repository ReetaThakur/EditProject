package com.masai.workmanagersample.works

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.airtelx.app.data.remote.DownloadAPI
import com.masai.workmanagersample.R
import com.masai.workmanagersample.model.Download
import com.masai.workmanagersample.model.RetrofitGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.*


class DownloadPeriodicWork(val appContext: Context, val workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private var totalFileSize = 0

    override fun doWork(): Result {

        CoroutineScope(Dispatchers.IO).launch {

            val data = inputData.getString("name")

            Log.d("doWork", "DownloadFileOnetimeWork")
            dislayNotification("OneTimework", "Downloading File")
            initDownload()

        }

        val output = Data.Builder()
        output.putString("key", "Completed")
        return Result.success(output.build())
    }


    private suspend fun initDownload() {
        val api: DownloadAPI = RetrofitGenerator.createService(
            DownloadAPI::class.java
        )

        try {
            downloadFile(api.downloadFile())
        } catch (e: IOException) {
            e.printStackTrace()
            CoroutineScope(Dispatchers.Main).launch {

                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Throws(IOException::class)
    private fun downloadFile(body: ResponseBody) {

        Log.d("doWork", "DownloadFileOnetimeWork2")


        var count: Int
        val data = ByteArray(1024 * 4)
        val fileSize = body.contentLength()
        val bis: InputStream = BufferedInputStream(body.byteStream(), 1024 * 8)
        val outputFile = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ), "file.zip"
        )
        val output: OutputStream = FileOutputStream(outputFile)
        var total: Long = 0
        val startTime = System.currentTimeMillis()
        var timeCount = 1
        while (bis.read(data).also { count = it } != -1) {
            total += count.toLong()
            totalFileSize = (fileSize / Math.pow(1024.0, 2.0)).toInt()
            val current = Math.round(total / Math.pow(1024.0, 2.0)).toDouble()
            val progress = (total * 100 / fileSize).toInt()
            val currentTime = System.currentTimeMillis() - startTime
            val download = Download()
            download.setTotalFileSize(totalFileSize)
            if (currentTime > 1000 * timeCount) {
                download.setCurrentFileSize(current.toInt())
                download.setProgress(progress)
                //sendNotification(download)
                timeCount++
            }
            output.write(data, 0, count)
        }
        //onDownloadComplete()
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(applicationContext, "Download complete", Toast.LENGTH_SHORT).show()
        }
        output.flush()
        output.close()
        bis.close()
    }


    private fun dislayNotification(task: String, desc: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "workExample",
                "workExample",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val builder = NotificationCompat.Builder(applicationContext, "workExample")
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(1, builder.build())
    }

}