package com.xyz.evaluations_week16

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * This is a View holder class which holds the views and the data is set to the views here
 */
class ResourceViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(dataModel: DataModel) {
        view.apply {
            tvId.text = dataModel.id.toString()
            tvName.text = dataModel.name
            tvYear.text = dataModel.year.toString()
            llItemLayout.setBackgroundColor(Color.parseColor(dataModel.color))
        }
    }
}