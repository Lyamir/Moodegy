package com.mobdeve.dobleteope.moodegy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.dobleteope.moodegy.data.Activity
import com.mobdeve.dobleteope.moodegy.data.ActivityEntry
import kotlinx.android.synthetic.main.stats_card.view.*

class ActivityStatsAdapter(
    private val activityList: List<Activity>,
    private val activityEntryList: List<ActivityEntry>
    ): RecyclerView.Adapter<ActivityStatsAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var activityView: TextView = itemView.card_name
        var totalView: TextView = itemView.card_total
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityStatsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stats_card, parent, false)
        return ActivityStatsAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityStatsAdapter.ViewHolder, position: Int) {
        val currentItem = activityList[position]
        holder.activityView.text = currentItem.name
        var counter = 0
        for(entry in activityEntryList){
            if(entry.activityID == currentItem.id){
                counter++
            }
        }
        holder.totalView.text = counter.toString()
    }

    override fun getItemCount() = activityList.size
}