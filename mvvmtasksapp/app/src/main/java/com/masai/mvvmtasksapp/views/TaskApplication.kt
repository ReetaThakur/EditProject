package com.masai.mvvmtasksapp.views

import android.app.Application
import com.masai.mvvmtasksapp.model.TaskDatabase
import com.masai.mvvmtasksapp.repository.TaskRepository


class TaskApplication : Application() {

    val taskDao by lazy {
        val roomDatabase = TaskDatabase.getRoomDatabase(this)
        roomDatabase.getTaskDao()
    }

    val repository by lazy {
        TaskRepository(taskDao)
    }

}