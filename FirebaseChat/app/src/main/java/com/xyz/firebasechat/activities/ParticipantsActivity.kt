package com.xyz.firebasechat.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.xyz.firebasechat.R
import com.xyz.firebasechat.adapter.UserAdapter
import com.xyz.firebasechat.interfaces.RecyclerViewItemClicked
import com.xyz.firebasechat.model.User
import kotlinx.android.synthetic.main.activity_participants.*

/**
 * This activity shows all the users that have logged into your app
 */
class ParticipantsActivity : AppCompatActivity(), RecyclerViewItemClicked {

    private lateinit var databaseReference: DatabaseReference
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participants)
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        /**
         * Get the node where the users are stored in database and fetch all the users and display it
         * in a recycler view where a user can click a particular participant and start chatting
         */
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (i in snapshot.children) {
                    val user: User = i.getValue(User::class.java)!!
                    /*
                    Do not show yourself into the participant list
                     */
                    if (user.userId != Firebase.auth.currentUser?.uid) {
                        userList.add(user)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun setAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(userList, this)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    override fun onItemClicked(position: Int, data: Any) {
        if (data is User) {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("receiverId", data.userId)
            startActivity(intent)
        }
    }
}