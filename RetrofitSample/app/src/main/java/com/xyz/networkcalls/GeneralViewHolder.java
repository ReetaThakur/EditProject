package com.xyz.networkcalls;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GeneralViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvTitle;
    private ImageView mIvThumbnail;

    public GeneralViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvTitle = itemView.findViewById(R.id.tvTitle);
        mIvThumbnail = itemView.findViewById(R.id.ivThumbnail);
    }

    public void setData(PhotosResponse response) {
        mTvTitle.setText(response.getTitle());
        Glide.with(mIvThumbnail).load(response.getThumbnailUrl()).centerCrop().into(mIvThumbnail);
    }
}
