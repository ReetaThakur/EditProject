package com.masai.workmanagersample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.*
import com.google.android.material.snackbar.Snackbar
import com.masai.workmanagersample.works.DownloadFileOnetimeWork


class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 1
    val workManager = WorkManager.getInstance(this)

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
                startDownload()
            } else {

            }
        }
    }

    private fun startDownload() {

        val constraint = Constraints.Builder()
        constraint.setRequiredNetworkType(NetworkType.CONNECTED)
        constraint.setRequiresCharging(false)

        val data = Data.Builder()
        data.putString("key", "Hello")

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(DownloadFileOnetimeWork::class.java)
        oneTimeWorkRequest.setConstraints(constraint.build())
        oneTimeWorkRequest.setInputData(data.build())
        oneTimeWorkRequest.addTag("download")

        workManager.enqueue(oneTimeWorkRequest.build())
    }
}