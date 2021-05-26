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
import com.mobdeve.dobleteope.moodegy.data.Mood
import com.mobdeve.dobleteope.moodegy.data.daos.MoodDao
import kotlinx.android.synthetic.main.moods_item.view.*

class ViewMoodsAdapter(
    var context: Context,
    var moodList: MutableList<Mood>,
    private val moodDao: MoodDao
): RecyclerView.Adapter<ViewMoodsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var moodView: TextView
        var moodIDView: TextView
        var menu: ImageView
        init{
            moodView = itemView.moodname_textview
            moodIDView = itemView.moodid_textview
            menu = itemView.more_image
            menu.setOnClickListener{
                popupMenus(it)
            }
        }

        private fun popupMenus(v: View){

            val popupMenus = PopupMenu(context, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                var moodItem: Mood? = null
                for(mood in moodList){
                    if(mood.id == Integer.parseInt(moodIDView.text.toString())){
                        moodItem = mood
                        break
                    }
                }
                when(it.itemId){
                    R.id.edit_item ->{
                        if(moodItem != null){
                            val intent = Intent(context, UpdateMoodActivity::class.java)
                            intent.putExtra("mood", Gson().toJson(moodItem))
                            context.startActivity(intent)
                        }
                        true
                    }
                    R.id.delete_item->{
                        if(moodItem != null) {
                            moodDao.delete(moodItem)
                            moodList.remove(moodItem)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMoodsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.moods_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewMoodsAdapter.ViewHolder, position: Int) {
        val currentItem = moodList[position]
        holder.moodView.text = currentItem.name
        holder.moodIDView.text = currentItem.id.toString()
    }

    override fun getItemCount() = moodList.size

}