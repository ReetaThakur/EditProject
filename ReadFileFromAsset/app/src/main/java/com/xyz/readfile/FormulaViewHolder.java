package com.xyz.readfile;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FormulaViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvFormulaName;
    private TextView mTvFormulaEquation;

    public FormulaViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvFormulaEquation = itemView.findViewById(R.id.tvFormulaUrl);
        mTvFormulaName = itemView.findViewById(R.id.tvFormulaName);
    }

    public void setData(FormulaModel formulaModel) {
        mTvFormulaName.setText(formulaModel.getFormulae());
        mTvFormulaEquation.setText(formulaModel.getUrl());
    }
}
