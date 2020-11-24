package com.xyz.firebasechat.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.xyz.firebasechat.R
import com.xyz.firebasechat.adapter.ChatAdapter
import com.xyz.firebasechat.model.Chat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is the chat screen where a user can talk to any participant those who have registered into
 * your app
 */
class ChatActivity : AppCompatActivity() {

    private var chatList = mutableListOf<Chat>()
    private lateinit var chatAdapter: ChatAdapter
    private var receiverId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerAdapter()
        receiverId = intent.getStringExtra("receiverId")

        /**
         * Sending a message and creating a node to store all the messages
         */
        btnSend.setOnClickListener {

            /*
            We are storing the message, current time, logged in user id and receiver id
             */
            val chatMessage = Chat(
                etChat.text.toString().trim(),
                System.currentTimeMillis().toString(),
                Firebase.auth.uid!!, receiverId!!
            )

            FirebaseDatabase.getInstance().reference.child("messages").push().setValue(chatMessage)
            etChat.setText("")
        }

        readMessages()
    }

    /**
     * Read the incoming and sent messages and show it in a recycler view
     */
    private fun readMessages() {
        FirebaseDatabase.getInstance().getReference("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatList.clear()
                    for (i in snapshot.children) {
                        val chat: Chat = i.getValue(Chat::class.java)!!

                        /*
                        The below condition will show the messages that are related to a sender and participant
                         */
                        if ((chat.receiver == Firebase.auth.currentUser?.uid && chat.sender == receiverId)
                            || chat.receiver == receiverId && chat.sender == Firebase.auth.currentUser?.uid
                        ) {
                            chatList.add(chat)
                        }
                    }

                    chatAdapter.updateList(chatList)
                    /*
                    This will scroll the recycler view to the bottom position when every new message
                    comes
                     */
                    recyclerView.scrollToPosition(chatList.size - 1)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun setRecyclerAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(chatList)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = chatAdapter
        }
    }
}