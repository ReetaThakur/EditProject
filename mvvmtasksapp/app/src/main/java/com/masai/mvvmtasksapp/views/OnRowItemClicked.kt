package com.masai.mvvmtasksapp.views

import com.masai.mvvmtasksapp.model.TaskEntity

interface OnRowItemClicked {

    fun onEditButtonClicked(task: TaskEntity)
    fun onDeleteButtonClicked(task: TaskEntity)

}