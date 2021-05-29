package com.mobdeve.dobleteope.moodegy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mobdeve.dobleteope.moodegy.ActivityStatsAdapter
import com.mobdeve.dobleteope.moodegy.MoodStatsAdapter
import com.mobdeve.dobleteope.moodegy.R
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getDatabase(requireActivity().applicationContext)
        val moodEntryDao = db.moodEntryDao()
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()

        val moodList = moodDao.getAll()
        val moodEntryList = moodEntryDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()

        val updatedMoodList = mutableListOf<String>()
        val updatedActivityList = mutableListOf<String>()
        for(mood in moodList){
            updatedMoodList.add(mood.name)
        }

        for(moodEntry in moodEntryList){
            if(!updatedMoodList.contains(moodEntry.moodName))
                updatedMoodList.add(moodEntry.moodName)
        }

        for(activity in activityList){
            updatedActivityList.add(activity.name)
        }

        for(activityEntry in activityEntryList){
            if(!updatedActivityList.contains(activityEntry.activityName))
                updatedActivityList.add(activityEntry.activityName)
        }

        val moodAdapter = MoodStatsAdapter(updatedMoodList, moodEntryList)
        moodstats1_recyclerview.adapter = moodAdapter
        val mood_glm = GridLayoutManager(requireActivity().applicationContext, 4)
        moodstats1_recyclerview.layoutManager = mood_glm
        moodstats1_recyclerview.setHasFixedSize(true)

        val activityAdapter = ActivityStatsAdapter(updatedActivityList, activityEntryList)
        activitystats1_recyclerview.adapter = activityAdapter
        val activity_glm = GridLayoutManager(requireActivity().applicationContext, 4)
        activitystats1_recyclerview.layoutManager = activity_glm
        activitystats1_recyclerview.setHasFixedSize(true)
    }

}