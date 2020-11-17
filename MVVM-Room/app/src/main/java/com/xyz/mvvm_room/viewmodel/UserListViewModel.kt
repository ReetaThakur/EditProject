package com.xyz.mvvm_room.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xyz.mvvm_room.database.User
import com.xyz.mvvm_room.repository.UserListRepository

class UserListViewModel(context: Context) : ViewModel() {

    private val userListRepository = UserListRepository(context)

    /**
     * This will fetch the list of users from database and notify the activity with the help of
     * live data
     */
    fun fetchAllUsers(): LiveData<List<User>> {
        return userListRepository.fetchDataFromDB()
    }

    /**
     * Updates the user details in a background thread and once the database is changed
     * the livedata observer in the Dao i.e fetchAllUsers will be automatically called and notified
     * to the activity
     */
    fun updateUserDB(firstName: String, lastName: String, userId: Int) {
        userListRepository.updateUserDB(firstName, lastName, userId)
    }

    /**
     * Deletes a particular user from the database
     */
    fun deleteUser(user: User) {
        userListRepository.deleteUser(user)
    }
}