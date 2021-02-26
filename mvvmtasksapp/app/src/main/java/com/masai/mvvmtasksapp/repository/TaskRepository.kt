package com.masai.mvvmtasksapp.repository

import com.masai.mvvmtasksapp.model.TaskDAO
import com.masai.mvvmtasksapp.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(val taskDAO: TaskDAO) {

    fun addTask(taskEntity: TaskEntity){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.addNewTask(taskEntity)
        }
    }
}