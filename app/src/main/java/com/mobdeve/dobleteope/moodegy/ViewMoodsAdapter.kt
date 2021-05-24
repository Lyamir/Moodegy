package com.mobdeve.dobleteope.moodegy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.dobleteope.moodegy.data.Activity
import com.mobdeve.dobleteope.moodegy.data.Mood
import kotlinx.android.synthetic.main.moods_item.view.*

class ViewMoodsAdapter(
    private val moodList: List<Mood>
): RecyclerView.Adapter<ViewMoodsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var moodView: TextView = itemView.moodname_textview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMoodsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.moods_item, parent, false)
        return ViewMoodsAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewMoodsAdapter.ViewHolder, position: Int) {
        val currentItem = moodList[position]
        holder.moodView.text = currentItem.name
    }

    override fun getItemCount() = moodList.size

}