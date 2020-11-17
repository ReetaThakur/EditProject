package com.xyz.mvvm_room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.xyz.mvvm_room.database.User
import com.xyz.mvvm_room.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListRepository(private val context: Context) {

    fun fetchDataFromDB(): LiveData<List<User>> {
        return UserDatabase.getInstance(context)
            .userDao.getAllUsers()
    }

    fun updateUserDB(firstName: String, lastName: String, userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            UserDatabase.getInstance(context).userDao
                .updateUserDetails(firstName, lastName, userId)
        }
    }

    fun deleteUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            UserDatabase.getInstance(context).userDao
                .deleteUser(user)
        }
    }
}