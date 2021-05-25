package com.mobdeve.dobleteope.moodegy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import kotlinx.android.synthetic.main.activity_viewactivities.*

class ViewActivities: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewactivities)

        addactivity_btn.setOnClickListener{
            val intent = Intent(this, AddActivityActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getDatabase(this)
        val activityDao = db.activityDao()
        val activityList = activityDao.getAll()

        val adapter = ViewActivitiesAdapter(activityList)
        activitieslist_recyclerview.adapter = adapter
        activitieslist_recyclerview.layoutManager = LinearLayoutManager(this)
        activitieslist_recyclerview.setHasFixedSize(true)
    }
}