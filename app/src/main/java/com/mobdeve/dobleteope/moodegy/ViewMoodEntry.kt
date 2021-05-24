package com.mobdeve.dobleteope.moodegy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import kotlinx.android.synthetic.main.activity_viewmoodentry.*

class ViewMoodEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmoodentry)


    }

    override fun onStart() {
        super.onStart()
        val db = AppDatabase.getDatabase(this)
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()
        val photoDao = db.photoDao()
        val descriptionDao = db.descDao()

        val moodList = moodDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()

        val moodEntry = Gson().fromJson(intent.getStringExtra("moodEntry"), MoodEntry::class.java)
        viewentry_datetime.text = moodEntry.dateTime
        val description = descriptionDao.getActivity(moodEntry.id)
        if (description == null)
            viewentry_description.visibility = View.GONE
        else
            viewentry_description.text = description.text

        val photo = photoDao.getActivity(moodEntry.id)
        if(photo == null)
            viewentry_image.visibility = View.GONE

        for(mood in moodList){
            if(moodEntry.moodID == mood.id){
                viewentry_mood.text = mood.name
                break
            }
        }

        for(activityEntry in activityEntryList){
            if(activityEntry.moodEntryID == moodEntry.id){
                for(activity in activityList){
                    if(activityEntry.activityID == activity.id)
                        viewentry_activity.text = activity.name
                }
            }
        }



    }
}