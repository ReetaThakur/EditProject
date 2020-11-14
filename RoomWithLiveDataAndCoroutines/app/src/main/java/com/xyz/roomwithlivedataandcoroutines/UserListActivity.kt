package com.xyz.roomwithlivedataandcoroutines

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_users_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListActivity : AppCompatActivity(), RecyclerClickListener {

    private var userList = emptyList<User>()
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        fetchDataFromDB()
        setRecyclerAdapter()
    }

    private fun setRecyclerAdapter() {
        userListAdapter = UserListAdapter(userList, this)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = userListAdapter
    }

    private fun fetchDataFromDB() {
        val job = CoroutineScope(IO).launch {
            val userList = UserDatabase.getInstance(this@UserListActivity)
                .userDao.getAllUsers()
            this@UserListActivity.userList = userList
            updateUI()
        }

        job.invokeOnCompletion {
            println("debug : List fetched $userList")
        }
    }

    private suspend fun updateUI() {
        withContext(Main) {
            userListAdapter.updateData(userList)
        }
    }

    private suspend fun updateUIAfterEdit() {
        withContext(Main) {
            userListAdapter.updateData(userList)
            llEditLayout.visibility = View.GONE
        }
    }

    override fun onEditClicked(position: Int, user: User) {
        llEditLayout.visibility = View.VISIBLE
        etName.setText(user.firstName)
        etLastName.setText(user.lastName)
        btnEdit.setOnClickListener {
            updateUserDetails(user)
        }
    }

    private fun updateUserDetails(user: User) {
        CoroutineScope(IO).launch {

            val updateUserJob = async {
                return@async UserDatabase.getInstance(this@UserListActivity)
                    .userDao
                    .updateUserDetails(etName.getString(), etLastName.getString(), user.userId)
            }.await()

            val getNewListJob = async {
                return@async UserDatabase.getInstance(this@UserListActivity)
                    .userDao
                    .getAllUsers()
            }.await()

            this@UserListActivity.userList = getNewListJob
            updateUIAfterEdit()
        }
    }

    override fun onDeleteClicked(position: Int, user: User) {
        CoroutineScope(IO).launch {

            async {
                UserDatabase.getInstance(this@UserListActivity)
                    .userDao
                    .deleteUser(user)
            }
            val newList = async {
                return@async UserDatabase.getInstance(this@UserListActivity)
                    .userDao
                    .getAllUsers()
            }.await()
            userList = newList
            updateUI()
        }

    }

    private fun EditText.getString(): String {
        return text.toString()
    }
}