package com.xyz.roomwithlivedataandcoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xyz.roomwithlivedataandcoroutines.R
import com.xyz.roomwithlivedataandcoroutines.viewholder.UserListViewHolder
import com.xyz.roomwithlivedataandcoroutines.database.User
import com.xyz.roomwithlivedataandcoroutines.interfaces.RecyclerClickListener

class UserListAdapter(
    private var usersList: List<User>,
    private val listener: RecyclerClickListener
) :
    RecyclerView.Adapter<UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = usersList[position]
        holder.setData(user)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun updateData(userList: List<User>) {
        this.usersList = userList
        notifyDataSetChanged()
    }
}