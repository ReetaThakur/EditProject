package com.xyz.evaluations_week16

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * This class is the adapter class which supplies the data to the recycler view and is
 * responsible for the creation and binding of views
 */
class ResourceAdapter(private var dataModelList: List<DataModel>) :
    RecyclerView.Adapter<ResourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ResourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.setData(dataModel)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    /**
     * This method is used to update the data model list
     */
    fun updateData(dataModelList: List<DataModel>) {
        this.dataModelList = dataModelList
        notifyDataSetChanged()
    }
}