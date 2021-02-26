package com.masai.mvvmtasksapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.mvvmtasksapp.R
import com.masai.mvvmtasksapp.model.TaskDatabase
import com.masai.mvvmtasksapp.model.TaskEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnRowItemClicked {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var taskList = mutableListOf<TaskEntity>()

        val roomDatabase = TaskDatabase.getRoomDatabase(this)
        val taskDao = roomDatabase.getTaskDao()

        val adapter2 = SomeDataAdapter2(taskList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter2

        taskDao.getTasks().observe(this, Observer {
            taskList.clear()
            taskList.addAll(it)
            adapter2.notifyDataSetChanged()
        })

        /*CoroutineScope(Dispatchers.IO).launch {

            CoroutineScope(Dispatchers.Main).launch {
                if (tasksNEw != null && tasksNEw.size > 0){
                    taskList.clear()
                    taskList.addAll(tasksNEw)
                    adapter2.notifyDataSetChanged()
                }
            }

        }*/



        addTask.setOnClickListener {

            val title = titleEt.text.toString()
            val desc = descEt.text.toString()

            val newTaskEntity = TaskEntity(title, desc)

            CoroutineScope(Dispatchers.IO).launch {
                taskDao.addNewTask(newTaskEntity)
            }
        }
    }

    override fun onEditButtonClicked(task: TaskEntity) {
        TODO("Not yet implemented")
    }

    override fun onDeleteButtonClicked(task: TaskEntity) {
        TODO("Not yet implemented")
    }
}