package com.xyz.mvvm_room.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.xyz.mvvm_room.repository.UserRepository

class UserViewModel(private val context: Context) : ViewModel() {

    private val repository = UserRepository()

    /**
     * This method is used to insert the data to the database
     */
    fun insertDataToDatabase(firstName: String, lastName: String) {
        repository.insertDataToDatabase(firstName, lastName, context)
    }
}