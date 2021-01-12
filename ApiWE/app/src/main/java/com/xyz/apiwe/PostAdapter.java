package com.xyz.apiwe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<ResponseClass> responseClassList;

    public PostAdapter(List<ResponseClass> responseClassList) {
        this.responseClassList = responseClassList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        ResponseClass responseClass = responseClassList.get(position);
        holder.setData(responseClass);
    }

    @Override
    public int getItemCount() {
        return responseClassList.size();
    }

    public void updateRecyclerViewList(List<ResponseClass> responseClassList) {
        this.responseClassList = responseClassList;
        notifyDataSetChanged();
    }
}
