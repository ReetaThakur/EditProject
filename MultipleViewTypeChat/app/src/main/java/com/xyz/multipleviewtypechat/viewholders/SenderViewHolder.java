package com.xyz.multipleviewtypechat.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multipleviewtypechat.R;
import com.xyz.multipleviewtypechat.model.SenderChat;

public class SenderViewHolder  extends RecyclerView.ViewHolder {

    private TextView mTvSenderChatMessage;

    public SenderViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvSenderChatMessage = itemView.findViewById(R.id.tvSenderChatMessage);
    }

    public void setSenderData(SenderChat senderChat){
        mTvSenderChatMessage.setText(senderChat.getMessage());
    }
}
