package com.xyz.roomwithlivedataandcoroutines.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xyz.roomwithlivedataandcoroutines.database.User
import com.xyz.roomwithlivedataandcoroutines.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserListViewModel(private val context: Context) : ViewModel() {

    /**
     * This will fetch the list of users from database and notify the activity with the help of
     * live data
     */
    fun fetchDataFromDB(): LiveData<List<User>> {
        return UserDatabase.getInstance(context)
            .userDao.getAllUsers()
    }

    /**
     * Updates the user details in a background thread and once the database is changed
     * the livedata observer in the Dao i.e fetchAllUsers will be automatically called and notified
     * to the activity
     */
    fun updateUserDB(firstName: String, lastName: String, userId: Int) {
        CoroutineScope(IO).launch {
            UserDatabase.getInstance(context).userDao
                .updateUserDetails(firstName, lastName, userId)
        }
    }

    /**
     * Deletes a particular user from the database
     */
    fun deleteUser(user: User) {
        CoroutineScope(IO).launch {
            UserDatabase.getInstance(context).userDao
                .deleteUser(user)
        }
    }
}