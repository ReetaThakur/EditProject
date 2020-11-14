package com.xyz.roomwithlivedataandcoroutines

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class UserListViewHolder(
    private val view: View,
    private val listener: RecyclerClickListener
) : RecyclerView.ViewHolder(view) {

    fun setData(user: User) {
        view.apply {
            tvName.text = user.firstName
            tvLastName.text = user.lastName

            ivEdit.setOnClickListener {
                listener.onEditClicked(adapterPosition, user)
            }

            ivDelete.setOnClickListener {
                listener.onDeleteClicked(adapterPosition, user)
            }
        }
    }
}