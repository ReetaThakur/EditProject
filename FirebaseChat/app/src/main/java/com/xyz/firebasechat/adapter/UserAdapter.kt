package com.xyz.firebasechat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xyz.firebasechat.R
import com.xyz.firebasechat.interfaces.RecyclerViewItemClicked
import com.xyz.firebasechat.model.User
import com.xyz.firebasechat.viewholders.UserViewHolder

class UserAdapter(
    private val userList: List<User>,
    private val recyclerViewItemClicked: RecyclerViewItemClicked
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view, recyclerViewItemClicked)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.setData(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}