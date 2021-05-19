package com.mobdeve.dobleteope.moodegy

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.dobleteope.moodegy.data.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_stats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }

        btn_addmood.setOnClickListener {
            val intent = Intent(this, AddMoodActivity::class.java)
            startActivity(intent)
        }

        btn_addactivity.setOnClickListener {
            val intent = Intent(this, AddActivityActivity::class.java)
            startActivity(intent)
        }

        btn_addmoodentry.setOnClickListener{
            val intent = Intent(this, AddMoodEntryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val db = AppDatabase.getDatabase(this)
        val moodEntryDao = db.moodEntryDao()
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()

        val moodList = moodDao.getAll()
        val moodEntryList = moodEntryDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()

        val adapter = MoodEntryAdapter(moodEntryList, moodList, activityList, activityEntryList)
        main_recyclerview.adapter = adapter
        val llm = LinearLayoutManager(this)
        llm.reverseLayout = true
        llm.stackFromEnd = true
        main_recyclerview.layoutManager = llm
        main_recyclerview.setHasFixedSize(true)
    }
}