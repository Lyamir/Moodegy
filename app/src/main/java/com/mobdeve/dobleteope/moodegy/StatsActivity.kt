package com.mobdeve.dobleteope.moodegy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val db = AppDatabase.getDatabase(this)
        val moodEntryDao = db.moodEntryDao()
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()

        val moodList = moodDao.getAll()
        val moodEntryList = moodEntryDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()

        val moodAdapter = MoodStatsAdapter(moodList, moodEntryList)
        moodstats_recyclerview.adapter = moodAdapter
        val mood_glm = GridLayoutManager(this, 4)
        moodstats_recyclerview.layoutManager = mood_glm
        moodstats_recyclerview.setHasFixedSize(true)

        val activityAdapter = ActivityStatsAdapter(activityList, activityEntryList)
        activitystats_recyclerview.adapter = activityAdapter
        val activity_glm = GridLayoutManager(this, 4)
        activitystats_recyclerview.layoutManager = activity_glm
        activitystats_recyclerview.setHasFixedSize(true)
    }
}