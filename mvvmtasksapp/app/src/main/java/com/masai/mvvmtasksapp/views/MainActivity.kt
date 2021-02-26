package com.masai.mvvmtasksapp.views

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.mvvmtasksapp.R
import com.masai.mvvmtasksapp.model.TaskDAO
import com.masai.mvvmtasksapp.model.TaskDatabase
import com.masai.mvvmtasksapp.model.TaskEntity
import com.masai.mvvmtasksapp.viewmodel.TaskViewModel
import com.masai.mvvmtasksapp.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnRowItemClicked {

    var taskList = mutableListOf<TaskEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter2 = SomeDataAdapter2(taskList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter2

        val appClass = application as TasksApplication

        val repository = appClass.repository
        val viewModelFactory = TaskViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TaskViewModel::class.java)


        addTask.setOnClickListener {

            val title = titleEt.text.toString()
            val desc = descEt.text.toString()

            val newTaskEntity = TaskEntity(title, desc)

            viewModel.addTask(newTaskEntity)

        }
    }

    override fun onEditButtonClicked(task: TaskEntity) {


    }

    override fun onDeleteButtonClicked(task: TaskEntity) {


    }
}