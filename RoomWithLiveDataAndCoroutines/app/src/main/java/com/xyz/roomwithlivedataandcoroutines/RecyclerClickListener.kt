package com.xyz.roomwithlivedataandcoroutines

interface RecyclerClickListener {

    fun onEditClicked(position: Int, user: User)

    fun onDeleteClicked(position: Int, user: User)
}