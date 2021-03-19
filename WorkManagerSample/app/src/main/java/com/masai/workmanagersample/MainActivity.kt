package com.masai.workmanagersample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.work.*
import com.masai.workmanagersample.works.DownloadFileOnetimeWork
import com.masai.workmanagersample.works.DownloadPeriodicWork
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()

    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //startDownload()
                startPeriodicWork()
            } else {

            }
        }
    }

    private fun startDownload() {

        val constraints = Constraints.Builder()
        constraints.setRequiredNetworkType(NetworkType.CONNECTED)
        constraints.setRequiresCharging(false)


        val data = Data.Builder()
        data.putString("key", "Hello")

        val oneTimeWork = OneTimeWorkRequest.Builder(DownloadFileOnetimeWork::class.java)
        oneTimeWork.setConstraints(constraints.build())
        oneTimeWork.setInputData(data.build())
        oneTimeWork.addTag("downlaodTag")

        val workManager = WorkManager.getInstance(this)


        workManager.enqueue(oneTimeWork.build())

    }


    fun startPeriodicWork() {
        val constraints = Constraints.Builder()
        constraints.setRequiredNetworkType(NetworkType.CONNECTED)
        constraints.setRequiresBatteryNotLow(true)
        constraints.setRequiresCharging(false)

        val data = Data.Builder()
        data.putString("name", "MyFile")

        val periodicWork = PeriodicWorkRequest
            .Builder(
                DownloadPeriodicWork::class.java,
                60,
                TimeUnit.MINUTES
            )
        //periodicWork.setInitialDelay(1, TimeUnit.MINUTES)
        periodicWork.setConstraints(constraints.build())
        periodicWork.setInputData(data.build())
        periodicWork.addTag("periodic")

        val work = periodicWork.build()

        val workManager = WorkManager
            .getInstance(this)

        workManager.enqueue(work)


        workManager.cancelAllWorkByTag("periodic")

        workManager.cancelAllWork()

        workManager.getWorkInfoByIdLiveData(work.id)
            .observe(this, Observer {

                if (it.state == WorkInfo.State.SUCCEEDED) {
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "${it.outputData.getString("key")}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })

    }
}