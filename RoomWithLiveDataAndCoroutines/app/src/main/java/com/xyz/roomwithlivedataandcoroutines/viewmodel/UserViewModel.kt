package com.xyz.roomwithlivedataandcoroutines.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.xyz.roomwithlivedataandcoroutines.database.User
import com.xyz.roomwithlivedataandcoroutines.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val context: Context) : ViewModel() {

    /**
     * This method is used to insert the data to the database
     */
    fun insertDataToDatabase(firstName: String, lastName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user =
                User(firstName = firstName, lastName = lastName)
            UserDatabase.getInstance(context).userDao.insertUser(user)
        }
    }
}