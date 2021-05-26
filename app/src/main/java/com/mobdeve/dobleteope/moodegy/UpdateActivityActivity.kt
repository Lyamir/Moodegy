package com.mobdeve.dobleteope.moodegy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.data.Activity
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import kotlinx.android.synthetic.main.activity_updateactivity.*

class UpdateActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateactivity)

        val db = AppDatabase.getDatabase(this)
        val activityDao = db.activityDao()
        val activity: Activity = Gson().fromJson(intent.getStringExtra("activity"), Activity::class.java)
        editactivity_edittext.setText(activity.name)

        editactivity_edittext.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                editactivity_btn.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        editactivity_btn.setOnClickListener{
            val updatedActivity = Activity(activity.id, editactivity_edittext.text.toString())
            activityDao.update(updatedActivity)
            finish()
        }

    }


}