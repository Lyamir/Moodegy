package com.mobdeve.dobleteope.moodegy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.dobleteope.moodegy.data.Activity
import kotlinx.android.synthetic.main.activities_item.view.*

class ViewActivitiesAdapter(
    private val activityList: List<Activity>
    ): RecyclerView.Adapter<ViewActivitiesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var activityView: TextView = itemView.activityname_textview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewActivitiesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activities_item, parent, false)
        return ViewActivitiesAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewActivitiesAdapter.ViewHolder, position: Int) {
        val currentItem = activityList[position]
        holder.activityView.text = currentItem.name
    }

    override fun getItemCount() = activityList.size

}