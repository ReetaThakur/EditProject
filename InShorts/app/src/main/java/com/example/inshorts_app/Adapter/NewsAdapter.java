package com.example.inshorts_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inshorts_app.R;
import com.example.inshorts_app.Response.DataItem;
import com.example.inshorts_app.ViewHolders.NewsViewHolder;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private List<DataItem> dataItemsList;

    public NewsAdapter(List<DataItem> dataItemsList) {
        this.dataItemsList = dataItemsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        DataItem dataItem = dataItemsList.get(position);
        holder.setData(dataItem);

    }

    @Override
    public int getItemCount() {
        return dataItemsList.size();
    }
    public void updateData(List<DataItem> dataItemsList){
        this.dataItemsList = dataItemsList;
        notifyDataSetChanged();
    }
}
