package com.masai.mvvmtasksapp.repository

import androidx.lifecycle.LiveData
import com.masai.mvvmtasksapp.model.TaskDAO
import com.masai.mvvmtasksapp.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(val taskDAO: TaskDAO) {

    fun addTask(taskEntity: TaskEntity){
        CoroutineScope(Dispatchers.IO).launch{
            taskDAO.addNewTask(taskEntity)
        }
    }

    fun getTasks(): LiveData<List<TaskEntity>> {
        return taskDAO.getTasks()
    }

    fun updateTask(taskEntity: TaskEntity){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.updateTask(taskEntity)
        }
    }

    fun deleteTask(taskEntity: TaskEntity){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.deleteTask(taskEntity)
        }
    }

}