package com.xyz.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private List<Menu> menuList;
    private ItemClickListener itemClickListener;

    public MenuAdapter(List<Menu> menuList, ItemClickListener itemClickListener) {
        this.menuList = menuList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items, parent, false);
        return new MenuViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.setData(menu);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
