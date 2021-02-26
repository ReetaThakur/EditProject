package com.masai.mvvmtasksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.masai.mvvmtasksapp.model.TaskEntity
import com.masai.mvvmtasksapp.repository.TaskRepository

class TaskViewModel(val repository: TaskRepository) : ViewModel() {

    fun addTask(taskEntity: TaskEntity){
        repository.addTask(taskEntity)
    }

}