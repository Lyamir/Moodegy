package com.mobdeve.dobleteope.moodegy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.Mood
import kotlinx.android.synthetic.main.activity_addmood.*

class AddMoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmood)

        addmood_btn.isEnabled = false

        addmood_edittext.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                addmood_btn.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
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

        val db = AppDatabase.getDatabase(this)
        val moodDao = db.moodDao()

        addmood_btn.setOnClickListener(){
            moodDao.insert(Mood(0, addmood_edittext.text.toString()))
            finish()
        }
    }
}
