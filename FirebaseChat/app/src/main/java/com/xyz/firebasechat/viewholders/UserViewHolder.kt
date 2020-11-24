package com.xyz.firebasechat.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xyz.firebasechat.interfaces.RecyclerViewItemClicked
import com.xyz.firebasechat.model.User
import kotlinx.android.synthetic.main.user_layout.view.*

class UserViewHolder(
    private val view: View,
    private val recyclerViewItemClicked: RecyclerViewItemClicked
) : RecyclerView.ViewHolder(view) {

    fun setData(user: User) {
        view.apply {
            tvUserName.text = user.name
            llUsers.setOnClickListener {
                recyclerViewItemClicked.onItemClicked(adapterPosition, user)
            }
        }
    }

}