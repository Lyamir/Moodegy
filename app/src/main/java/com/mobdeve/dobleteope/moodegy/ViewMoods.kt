package com.mobdeve.dobleteope.moodegy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import kotlinx.android.synthetic.main.activity_viewactivities.*
import kotlinx.android.synthetic.main.activity_viewmoods.*

class ViewMoods: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmoods)
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getDatabase(this)
        val moodDao= db.moodDao()
        val moodList = moodDao.getAll()

        val adapter = ViewMoodsAdapter(moodList)
        moodslist_recyclerview.adapter = adapter
        moodslist_recyclerview.layoutManager = LinearLayoutManager(this)
        moodslist_recyclerview.setHasFixedSize(true)
    }
}