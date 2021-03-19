package com.masai.workmanagersample.works

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkFileDownlaod(val appOntext: Context, workerParameters: WorkerParameters) : Worker(appOntext, workerParameters) {

    override fun doWork(): Result {

        return Result.success()
    }

}