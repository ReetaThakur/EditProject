package com.xyz.firebasechat.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xyz.firebasechat.model.Chat
import kotlinx.android.synthetic.main.chat_right_layout.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ChatSenderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(chat: Chat) {
        view.apply {
            tvChatMessage.text = chat.message
            val date = Date(chat.timeStamp.toLong())
            val formatter: DateFormat = SimpleDateFormat("HH:mm")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateFormatted: String = formatter.format(date)
            tvTime.text = dateFormatted
        }
    }
}