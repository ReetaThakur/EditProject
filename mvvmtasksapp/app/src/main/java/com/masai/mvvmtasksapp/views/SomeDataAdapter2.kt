package com.masai.mvvmtasksapp.views

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.masai.mvvmtasksapp.R
import com.masai.mvvmtasksapp.model.TaskEntity

class SomeDataAdapter2(private val tasks: List<TaskEntity>, val listner: OnRowItemClicked) : RecyclerView.Adapter<SomeDataAdapter2.ClassViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {

        holder.mTvTitle.text = tasks[position].title
        holder.mDesc.text = tasks[position].desc

        holder.editBtn.setOnClickListener {
            listner.onEditButtonClicked(tasks[position])
        }

        holder.deleteBtn.setOnClickListener {
            listner.onDeleteButtonClicked(tasks[position])
        }

    }

    override fun getItemCount(): Int {
        return tasks.size
    }


    class ClassViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val mTvTitle: TextView =
            view.findViewById<TextView>(R.id.title)

        val mDesc: TextView =
            view.findViewById<TextView>(R.id.desc)


        val editBtn = view.findViewById<Button>(R.id.edit)
        val deleteBtn = view.findViewById<Button>(R.id.delete)


    }

}