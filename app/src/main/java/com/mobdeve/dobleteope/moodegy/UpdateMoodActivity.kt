package com.mobdeve.dobleteope.moodegy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.Mood
import kotlinx.android.synthetic.main.activity_updatemood.*

class UpdateMoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatemood)

        val db = AppDatabase.getDatabase(this)
        val moodDao = db.moodDao()
        val mood: Mood = Gson().fromJson(intent.getStringExtra("mood"), Mood::class.java)
        editmood_edittext.setText(mood.name)

        editmood_edittext.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                editmood_btn.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty() && s.toString() != mood.name
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

        editmood_btn.setOnClickListener{
            val updatedMood = Mood(mood.id, editmood_edittext.text.toString())
            moodDao.update(updatedMood)
            finish()
        }
    }
}
