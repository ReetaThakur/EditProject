package com.xyz.roomwithlivedataandcoroutines.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xyz.roomwithlivedataandcoroutines.R
import com.xyz.roomwithlivedataandcoroutines.adapter.UserListAdapter
import com.xyz.roomwithlivedataandcoroutines.database.User
import com.xyz.roomwithlivedataandcoroutines.interfaces.RecyclerClickListener
import com.xyz.roomwithlivedataandcoroutines.viewmodel.UserListViewModel
import com.xyz.roomwithlivedataandcoroutines.viewmodel.UserListViewModelFactory
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
        userListViewModel.fetchDataFromDB().observe(this, {
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