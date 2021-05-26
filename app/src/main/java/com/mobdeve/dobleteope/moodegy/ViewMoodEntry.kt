package com.mobdeve.dobleteope.moodegy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import kotlinx.android.synthetic.main.activity_viewmoodentry.*

class ViewMoodEntry : AppCompatActivity() {
    var moodEntry: MoodEntry? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmoodentry)

        editentry_btn.setOnClickListener{
            val intent = Intent(this, UpdateMoodEntryActivity::class.java)
            intent.putExtra("moodEntry", Gson().toJson(moodEntry))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getDatabase(this)
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()
        val photoDao = db.photoDao()
        val descriptionDao = db.descDao()

        val moodList = moodDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()

        moodEntry = Gson().fromJson(intent.getStringExtra("moodEntry"), MoodEntry::class.java)
        if(moodEntry != null){
            val newMoodEntry = moodEntry as MoodEntry
            viewentry_datetime.text = newMoodEntry.dateTime
            val description = descriptionDao.getActivity(newMoodEntry.id)
            if (description == null)
                viewentry_description.visibility = View.GONE
            else
                viewentry_description.text = description.text

            val photo = photoDao.getActivity(newMoodEntry.id)
            if(photo == null)
                viewentry_image.visibility = View.GONE
            else
                viewentry_image.setImageBitmap(photo.photo)

            for(mood in moodList){
                if(newMoodEntry.moodID == mood.id){
                    viewentry_mood.text = mood.name
                    break
                }
            }

            for(activityEntry in activityEntryList){
                if(activityEntry.moodEntryID == newMoodEntry.id){
                    for(activity in activityList){
                        if(activityEntry.activityID == activity.id)
                            viewentry_activity.text = activity.name
                    }
                }
            }
        }




    }
}