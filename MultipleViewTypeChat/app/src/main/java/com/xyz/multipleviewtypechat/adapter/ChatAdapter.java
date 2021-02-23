package com.xyz.multipleviewtypechat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multipleviewtypechat.R;
import com.xyz.multipleviewtypechat.model.BaseModel;
import com.xyz.multipleviewtypechat.model.ReceiverChat;
import com.xyz.multipleviewtypechat.model.SenderChat;
import com.xyz.multipleviewtypechat.viewholders.ReceiverViewHolder;
import com.xyz.multipleviewtypechat.viewholders.SenderViewHolder;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseModel> baseModelList;

    public ChatAdapter(List<BaseModel> baseModelList) {
        this.baseModelList = baseModelList;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_sender_layout, parent, false);
                return new SenderViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_receiver_layout, parent, false);
                return new ReceiverViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = baseModelList.get(position).getItemType();
        BaseModel model = baseModelList.get(position);
        switch (viewType) {
            case 1:
                if (holder instanceof SenderViewHolder) {
                    ((SenderViewHolder) holder).setSenderData((SenderChat) model);
                }
                break;
            case 2:
                if (holder instanceof ReceiverViewHolder) {
                    ((ReceiverViewHolder) holder).setData((ReceiverChat) model);
                }
                break;
        }
    }

    /**
     * This method returns the view type based on which we need to populate the view in
     * the onCreateViewHolder()
     *
     * @return if 1 then populate Sender layout , if 2 populate Receivers layout
     */
    @Override
    public int getItemViewType(int position) {
        return baseModelList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return baseModelList.size();
    }
}
