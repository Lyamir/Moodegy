package com.mobdeve.dobleteope.moodegy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.mobdeve.dobleteope.moodegy.data.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_constraint.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
        }

        val db = AppDatabase.getDatabase(this)
        val moodEntryDao = db.moodEntryDao()
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()

        moodList.text = moodDao.getAll().toString()
        activityList.text = activityDao.getAll().toString()



    }
}