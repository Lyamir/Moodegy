package com.mobdeve.dobleteope.moodegy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.dobleteope.moodegy.data.Activity
import com.mobdeve.dobleteope.moodegy.data.ActivityEntry
import com.mobdeve.dobleteope.moodegy.data.Mood
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import kotlinx.android.synthetic.main.moodentry_item.view.*

class MoodEntryAdapter(
    private val moodEntryList: List<MoodEntry>,
    private val moodList: List<Mood>,
    private val activityList: List<Activity>,
    private val activityEntryList: List<ActivityEntry>,
    private val listener: OnMoodEntryClickListener
    ): RecyclerView.Adapter<MoodEntryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var dateView: TextView = itemView.moodentry_date
        var moodView: TextView = itemView.moodentry_mood
        var activityView: TextView = itemView.moodentry_activity
        var linearlayout: LinearLayout = itemView.moodentry_ll

        init{
            itemView.moodentry_ll.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onMoodEntryClick(position)
            }
        }
    }

    interface OnMoodEntryClickListener{
        fun onMoodEntryClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodEntryAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.moodentry_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoodEntryAdapter.ViewHolder, position: Int) {
            val currentItem = moodEntryList[position]
            holder.dateView.text = currentItem.dateTime
            for (mood in moodList){
                if(mood.id == moodEntryList[position].moodID){
                    holder.moodView.text = mood.name
                    break
                }
            }

            for(activityEntry in activityEntryList){
                if(currentItem.id == activityEntry.moodEntryID){
                    for(activity in activityList){
                        if(activityEntry.activityID == activity.id){
                            holder.activityView.text = activity.name
                            break
                        }
                    }
                }
            }

    }

    override fun getItemCount() = moodEntryList.size
}