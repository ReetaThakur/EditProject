package com.xyz.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvItemName;
    private TextView mTvItemPrice;
    private TextView mTvRestaurantName;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View view) {
        mTvItemName = view.findViewById(R.id.tvFoodName);
        mTvItemPrice = view.findViewById(R.id.tvFoodPrice);
        mTvRestaurantName = view.findViewById(R.id.tvRestaurantName);
    }

    public void setData(Menu menu) {
        mTvItemName.setText(menu.getItemName());
        mTvItemPrice.setText( menu.getItemPrice() +"");
        mTvRestaurantName.setText(menu.getRestaurantName());
    }
}
