package com.xyz.apiwe;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvId;
    private TextView mTvUserId;
    private TextView mTvTitle;
    private TextView mTvBody;


    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvId = itemView.findViewById(R.id.tvId);
        mTvTitle = itemView.findViewById(R.id.tvTitle);
        mTvUserId = itemView.findViewById(R.id.tvUserId);
        mTvBody = itemView.findViewById(R.id.tvBody);
    }

    public void setData(ResponseClass responseClass) {
        mTvTitle.setText(responseClass.getTitle());
        mTvBody.setText(responseClass.getBody());
        mTvId.setText(responseClass.getId() +"");
        mTvUserId.setText(responseClass.getUserId()+"");
    }
}
