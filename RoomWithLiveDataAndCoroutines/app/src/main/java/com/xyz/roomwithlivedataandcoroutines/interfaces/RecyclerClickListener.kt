package com.xyz.roomwithlivedataandcoroutines.interfaces

import com.xyz.roomwithlivedataandcoroutines.database.User

interface RecyclerClickListener {

    fun onEditClicked(position: Int, user: User)

    fun onDeleteClicked(position: Int, user: User)
}