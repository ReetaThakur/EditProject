Room Database using MVVM Architecture
--------------------------------------

This app demonstrates about the using Room database using LiveData, Coroutines and using MVVM Architecture.

The UI Code is written  only in the `Activity` , `ViewModel` is used to pass the data between Activity and Repository and the `Repository` is the place where the data hub is present.

Lets look into a part of the code where we are fetching all the data from the Database, updating and editing the data.

1. Activity i.e `View`

```
package com.xyz.mvvm_room.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xyz.mvvm_room.*
import com.xyz.mvvm_room.adapter.UserListAdapter
import com.xyz.mvvm_room.database.User
import com.xyz.mvvm_room.interfaces.RecyclerClickListener
import com.xyz.mvvm_room.viewmodel.UserListViewModel
import com.xyz.mvvm_room.viewmodel.UserListViewModelFactory
import kotlinx.android.synthetic.main.activity_users_list.*

/**
 * This activity is used to fetch the data from the Database and display it in a recycler view
 * Also edit and delete is handled. All the database calls are moved separately to the view model class
 */
class UserListActivity : AppCompatActivity(), RecyclerClickListener {

    /*
    Let this list be empty as data will be fetched in the background from DB
     */
    private var userList = emptyList<User>()
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        userListViewModel = UserListViewModelFactory(this).create(UserListViewModel::class.java)

        /*
        Call this method in the view model class which makes a database fetch call and once the data
        is fetched with the help of live data it is notified
         */
        userListViewModel.fetchAllUsers().observe(this, {
            it?.let {
                this@UserListActivity.userList = it
                userListAdapter.updateData(userList)
            }
        })

        /*
        Set the recycler view adapter
         */
        setRecyclerAdapter()
    }

    private fun setRecyclerAdapter() {
        userListAdapter = UserListAdapter(userList, this)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = userListAdapter
    }

    override fun onEditClicked(position: Int, user: User) {
        llEditLayout.visibility = View.VISIBLE
        etName.setText(user.firstName)
        etLastName.setText(user.lastName)
        btnEdit.setOnClickListener {
            /*
            Once the edit and save button is clicked, pass the necessary details of the user to be updated
            to the view model and update the database
             */
            userListViewModel.updateUserDB(etName.getString(), etLastName.getString(), user.userId)
            llEditLayout.visibility = View.GONE
        }
    }

    override fun onDeleteClicked(position: Int, user: User) {
        userListViewModel.deleteUser(user)
    }

    private fun EditText.getString(): String {
        return text.toString()
    }
}
```

2. Viewmodel 

```
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
```

3. The `M` i.e Repository layer

```
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
```
