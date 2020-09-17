package com.xyz.recyclerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvItemName;
    private TextView mTvItemPrice;
    private TextView mTvRestaurantName;
    private LinearLayout mLlMenuCard;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        initViews(itemView);
        this.itemClickListener = itemClickListener;
    }

    private void initViews(View view) {
        mTvItemName = view.findViewById(R.id.tvFoodName);
        mTvItemPrice = view.findViewById(R.id.tvFoodPrice);
        mTvRestaurantName = view.findViewById(R.id.tvRestaurantName);
        mLlMenuCard = view.findViewById(R.id.llMenuCard);
    }

    public void setData(final Menu menu) {
        mTvItemName.setText(menu.getItemName());
        mTvItemPrice.setText(menu.getItemPrice() + "");
        mTvRestaurantName.setText(menu.getRestaurantName());
        mLlMenuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClicked(menu, getAdapterPosition());
            }
        });
    }
}
