package com.masai.mvvmtasksapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.mvvmtasksapp.R
import com.masai.mvvmtasksapp.model.TaskEntity
import com.masai.mvvmtasksapp.viewmodel.TaskViewModel
import com.masai.mvvmtasksapp.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnRowItemClicked {

    private lateinit var viewModel: TaskViewModel
    var taskList = mutableListOf<TaskEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter2 = SomeDataAdapter2(taskList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter2

        val appObj = application as TaskApplication
        val repository = appObj.repository

        val viewModelFactory = TaskViewModelFactory(repository)

        viewModel  = ViewModelProviders.of(this, viewModelFactory)
            .get(TaskViewModel::class.java)

        titleEt.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.textChanged(text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        addTask.setOnClickListener {
            viewModel.beforeText()
        }

        viewModel.getTextLiveData().observe(this, Observer {
            countTv.text = it
        })








        /*viewModel.getTasks().observe(this, Observer {
            taskList.clear()
            taskList.addAll(it)
            adapter2.notifyDataSetChanged()

        })
*/

        /*//ViewModel
        viewModel.getCountLiveData().observe(this, Observer {
            countTv.text = it
        })

        incBtn.setOnClickListener {
            viewModel.increment()
        }

        decBtn.setOnClickListener {
            viewModel.decrement()
        }
*/

        /*addTask.setOnClickListener {
            val title = titleEt.text.toString()
            val desc = descEt.text.toString()
            val newTask = TaskEntity(title, desc)
            viewModel.addTask(newTask)
        }*/
    }

    override fun onEditButtonClicked(task: TaskEntity) {
        /*task.title = "Edited"
        task.desc = "Edited des"
        viewModel.updateTask(task)*/

    }

    override fun onDeleteButtonClicked(task: TaskEntity) {
        //viewModel.deleteTask(task)
    }
}