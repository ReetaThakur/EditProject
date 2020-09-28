package com.xyz.networkcalls;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralViewHolder> {

    private List<PhotosResponse> responseList;

    public GeneralAdapter(List<PhotosResponse> responseList) {
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new GeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder holder, int position) {
        PhotosResponse response = responseList.get(position);
        holder.setData(response);
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public void updateData(List<PhotosResponse> responseList){
        this.responseList = responseList;
        notifyDataSetChanged();
    }
}
