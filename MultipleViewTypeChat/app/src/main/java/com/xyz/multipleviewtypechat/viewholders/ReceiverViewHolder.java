package com.xyz.multipleviewtypechat.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multipleviewtypechat.R;
import com.xyz.multipleviewtypechat.model.ReceiverChat;

public class ReceiverViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvReceiverChatMessage;
    private TextView mTvTime;

    public ReceiverViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvReceiverChatMessage = itemView.findViewById(R.id.tvReceiverChatMessage);
        mTvTime = itemView.findViewById(R.id.tvTime);
    }

    public void setData(ReceiverChat chat){
        mTvTime.setText(chat.getTime());
        mTvReceiverChatMessage.setText(chat.getMessage());
    }
}
