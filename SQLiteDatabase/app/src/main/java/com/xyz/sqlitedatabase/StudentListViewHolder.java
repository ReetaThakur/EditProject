package com.xyz.sqlitedatabase;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemClickListener listener;
    private TextView mTvName;
    private TextView mTvAddress;
    private CardView mCardView;

    public StudentListViewHolder(@NonNull View itemView, RecyclerItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvName = itemView.findViewById(R.id.tvName);
        mTvAddress = itemView.findViewById(R.id.tvAddress);
        mCardView = itemView.findViewById(R.id.cardView);

    }

    public void setData(final Student student) {
        mTvAddress.setText(student.getAddress());
        mTvName.setText(student.getName());

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(getAdapterPosition(), student);
            }
        });
    }
}
