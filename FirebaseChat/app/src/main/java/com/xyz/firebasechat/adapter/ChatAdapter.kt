package com.xyz.firebasechat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xyz.firebasechat.R
import com.xyz.firebasechat.model.Chat
import com.xyz.firebasechat.viewholders.ChatReceiverViewHolder
import com.xyz.firebasechat.viewholders.ChatSenderViewHolder

class ChatAdapter(private var chatList: List<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_TYPE_SENT = 1
    private val ITEM_TYPE_RECEIVED = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_SENT -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_right_layout, parent, false)
                return ChatSenderViewHolder(view)
            }

            ITEM_TYPE_RECEIVED -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_left_layout, parent, false)
                return ChatReceiverViewHolder(view)
            }
        }
        throw IllegalStateException("No matching views found")

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        if (holder is ChatSenderViewHolder) {
            holder.setData(chat)
        } else if (holder is ChatReceiverViewHolder) {
            holder.setData(chat)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun getItemViewType(position: Int): Int {
        /*
        Check if the sender is sending the message or the receiver is sending and handle the view
        type accordingly
         */
        return if (chatList[position].sender == Firebase.auth.currentUser?.uid) {
            ITEM_TYPE_SENT
        } else {
            ITEM_TYPE_RECEIVED
        }
    }

    fun updateList(chatList: List<Chat>) {
        this.chatList = chatList
        notifyDataSetChanged()
    }
}