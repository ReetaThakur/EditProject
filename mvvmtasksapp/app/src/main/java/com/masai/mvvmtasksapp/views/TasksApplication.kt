package com.masai.mvvmtasksapp.views

import android.app.Application
import com.masai.mvvmtasksapp.model.TaskDatabase
import com.masai.mvvmtasksapp.repository.TaskRepository

class TasksApplication : Application() {


    //Interact with our room database
    val taskDao by lazy {
        val roomDatabase = TaskDatabase.getRoomDatabase(this)
        roomDatabase.getTaskDao()
    }

    //repositoy which is responsible for getting the data and returning to viewmodel
    val repository by lazy {
        TaskRepository(taskDao)
    }

}