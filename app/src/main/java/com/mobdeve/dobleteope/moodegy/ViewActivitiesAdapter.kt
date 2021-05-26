package com.mobdeve.dobleteope.moodegy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.data.Activity
import com.mobdeve.dobleteope.moodegy.data.Mood
import com.mobdeve.dobleteope.moodegy.data.daos.ActivityDao
import kotlinx.android.synthetic.main.activities_item.view.*

class ViewActivitiesAdapter(
    val context: Context,
    val activityList: MutableList<Activity>,
    private val activityDao: ActivityDao
    ): RecyclerView.Adapter<ViewActivitiesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var activityView: TextView
        var activityIDView: TextView
        var menu: ImageView
        init{
            activityView = itemView.activityname_textview
            activityIDView = itemView.activityid_textview
            menu = itemView.more_image
            menu.setOnClickListener{
                popupMenus(it)
            }
        }

        private fun popupMenus(v: View){

            val popupMenus = PopupMenu(context, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                var activityItem: Activity? = null
                for(activity in activityList){
                    if(activity.id == Integer.parseInt(activityIDView.text.toString())){
                        activityItem = activity
                        break
                    }
                }
                when(it.itemId){
                    R.id.edit_item ->{
                        if(activityItem != null){
                            val intent = Intent(context, UpdateActivityActivity::class.java)
                            intent.putExtra("activity", Gson().toJson(activityItem))
                            context.startActivity(intent)
                        }
                        true
                    }
                    R.id.delete_item->{
                        if(activityItem != null) {
                            activityDao.delete(activityItem)
                            activityList.remove(activityItem)
                            notifyDataSetChanged()
                        }
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewActivitiesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activities_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewActivitiesAdapter.ViewHolder, position: Int) {
        val currentItem = activityList[position]
        holder.activityView.text = currentItem.name
        holder.activityIDView.text = currentItem.id.toString()
    }

    override fun getItemCount() = activityList.size

}