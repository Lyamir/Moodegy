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
    lateinit var moodEntry: MoodEntry
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
        val moodEntryDao = db.moodEntryDao()
        val activityEntryDao = db.activityEntryDao()
        val photoDao = db.photoDao()
        val descriptionDao = db.descDao()

        val activityEntryList = activityEntryDao.getAll()

        moodEntry = Gson().fromJson(intent.getStringExtra("moodEntry"), MoodEntry::class.java)

        moodEntry = moodEntryDao.getMoodEntry(moodEntry.id)

        viewentry_datetime.text = moodEntry.dateTime
        val description = descriptionDao.getActivity(moodEntry.id)
        if (description == null)
            viewentry_description.visibility = View.GONE
        else
            viewentry_description.text = description.text

        val photo = photoDao.getActivity(moodEntry.id)
        if(photo == null)
            viewentry_image.visibility = View.GONE
        else
            viewentry_image.setImageBitmap(photo.photo)

        viewentry_mood.text = moodEntry.moodName

        for(activityEntry in activityEntryList){
            if(activityEntry.moodEntryID == moodEntry.id){
                viewentry_activity.text = activityEntry.activityName
            }
        }


    }
}