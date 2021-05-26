package com.mobdeve.dobleteope.moodegy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_updatemoodentry.*

class UpdateMoodEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatemoodentry)

        editactivities_textview.setOnClickListener{
            val intent = Intent(this, ViewActivities::class.java)
            startActivity(intent)
        }

        editmoods_textview.setOnClickListener{
            val intent = Intent(this, ViewMoods::class.java)
            startActivity(intent)
        }

    }
}
