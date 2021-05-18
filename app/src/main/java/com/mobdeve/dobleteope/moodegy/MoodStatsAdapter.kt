package com.mobdeve.dobleteope.moodegy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.dobleteope.moodegy.data.Mood
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import kotlinx.android.synthetic.main.stats_card.view.*

class MoodStatsAdapter(
    private val moodList: List<Mood>,
    private val moodEntryList: List<MoodEntry>
    ): RecyclerView.Adapter<MoodStatsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var moodView: TextView = itemView.card_name
        var totalView: TextView = itemView.card_total
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodStatsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stats_card, parent, false)
        return MoodStatsAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoodStatsAdapter.ViewHolder, position: Int) {
        val currentItem = moodList[position]
        holder.moodView.text = currentItem.name
        var counter = 0
        for(entry in moodEntryList){
            if(entry.moodID == currentItem.id){
                counter++
            }
        }
        holder.totalView.text = counter.toString()
    }

    override fun getItemCount() = moodList.size
}