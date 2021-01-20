package com.xyz.readfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FormulaAdapter extends RecyclerView.Adapter<FormulaViewHolder> {

    private List<FormulaModel> formulaModelList;

    public FormulaAdapter(List<FormulaModel> formulaModelList) {
        this.formulaModelList = formulaModelList;
    }

    @NonNull
    @Override
    public FormulaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new FormulaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormulaViewHolder holder, int position) {
        FormulaModel formulaModel = formulaModelList.get(position);
        holder.setData(formulaModel);
    }

    @Override
    public int getItemCount() {
        return formulaModelList.size();
    }

    public void updateData(List<FormulaModel> formulaModelList){
        this.formulaModelList = formulaModelList;
        notifyDataSetChanged();
    }
}
