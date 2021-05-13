package com.mobdeve.dobleteope.moodegy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import com.mobdeve.dobleteope.moodegy.data.MoodEntryDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_constraint.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "MoodegyDB"
        ).allowMainThreadQueries().build()

        val moodEntryDao = db.moodEntryDao()



    }
}