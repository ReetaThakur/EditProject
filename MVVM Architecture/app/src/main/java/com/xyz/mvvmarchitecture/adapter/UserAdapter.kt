package com.xyz.mvvmarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xyz.mvvmarchitecture.R
import com.xyz.mvvmarchitecture.model.DataModel
import com.xyz.mvvmarchitecture.viewholder.UserViewHolder

class UserAdapter(private var dataModelList: List<DataModel>) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.setData(dataModel)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    fun updateList(modelList: List<DataModel>) {
        dataModelList = modelList
        notifyDataSetChanged()
    }

}