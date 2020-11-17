package com.xyz.mvvmarchitecture.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xyz.mvvmarchitecture.model.DataModel
import kotlinx.android.synthetic.main.item_layout.view.*

class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(dataModel: DataModel) {
        view.apply {
            Glide.with(ivAvatar).load(dataModel.avatar).into(ivAvatar)
            tvId.text = dataModel.id.toString()
            tvFirstName.text = dataModel.firstName
            tvLastName.text = dataModel.lastName
            tvEmail.text = dataModel.email
        }
    }
}