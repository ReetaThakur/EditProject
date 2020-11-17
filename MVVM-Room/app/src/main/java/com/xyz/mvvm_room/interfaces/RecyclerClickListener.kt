package com.xyz.mvvm_room.interfaces

import com.xyz.mvvm_room.database.User

interface RecyclerClickListener {

    fun onEditClicked(position: Int, user: User)

    fun onDeleteClicked(position: Int, user: User)
}