package com.example.inshorts_app.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inshorts_app.R;
import com.example.inshorts_app.Response.DataItem;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView title, content;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        imageView = itemView.findViewById(R.id.imageNews);
        title = itemView.findViewById(R.id.titleNews);
        content = itemView.findViewById(R.id.contentNews);

    }

    public void setData(DataItem data) {
        Glide.with(itemView).load(data.getImageUrl()).placeholder(R.drawable.error).into(imageView);
        title.setText(data.getTitle());
        content.setText(data.getContent());
    }
}
