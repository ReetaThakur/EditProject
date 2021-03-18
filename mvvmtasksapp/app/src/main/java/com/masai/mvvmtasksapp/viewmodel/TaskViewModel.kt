package com.masai.mvvmtasksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masai.mvvmtasksapp.model.TaskEntity
import com.masai.mvvmtasksapp.repository.TaskRepository

class TaskViewModel(val repository: TaskRepository) : ViewModel(){

    companion object{
        var count = 0
    }

     val textChangeLiveData = MutableLiveData<String>()

     val textChangeLiveData2 = MutableLiveData<String>()

    val mediatorLiveData = MediatorLiveData<String>()

    fun getTextLiveData(): LiveData<String>{
        mediatorLiveData.addSource(textChangeLiveData, Observer {
            mediatorLiveData.value = it
        })

        mediatorLiveData.addSource(textChangeLiveData2, Observer {
            mediatorLiveData.value = it
        })

        return mediatorLiveData
    }

    fun textChanged(newText: String){
        textChangeLiveData.value = newText
    }

    fun beforeText(){
        textChangeLiveData2.value = "Button clicked"
    }

    fun addTask(taskEntity: TaskEntity){
        repository.addTask(taskEntity)
    }

    fun getTasks(): LiveData<List<TaskEntity>>{
        return repository.getTasks()
    }

    fun updateTask(taskEntity: TaskEntity){
        repository.updateTask(taskEntity)
    }

    fun deleteTask(taskEntity: TaskEntity){
        repository.deleteTask(taskEntity)
    }


}