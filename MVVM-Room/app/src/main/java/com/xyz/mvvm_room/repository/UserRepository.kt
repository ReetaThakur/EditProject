package com.xyz.mvvm_room.repository

import android.content.Context
import com.xyz.mvvm_room.database.User
import com.xyz.mvvm_room.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository {

    fun insertDataToDatabase(firstName: String, lastName: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val user =
                User(firstName = firstName, lastName = lastName)
            UserDatabase.getInstance(context).userDao.insertUser(user)
        }
    }
}