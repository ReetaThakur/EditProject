package com.masai.mvvmtasksapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.mvvmtasksapp.repository.TaskRepository

class TaskViewModelFactory(val repository: TaskRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return TaskViewModel(repository) as T
    }
}