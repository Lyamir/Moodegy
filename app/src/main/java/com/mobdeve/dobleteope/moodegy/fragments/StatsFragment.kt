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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        val moodAdapter = MoodStatsAdapter(moodList, moodEntryList)
        moodstats1_recyclerview.adapter = moodAdapter
        val mood_glm = GridLayoutManager(requireActivity().applicationContext, 4)
        moodstats1_recyclerview.layoutManager = mood_glm
        moodstats1_recyclerview.setHasFixedSize(true)

        val activityAdapter = ActivityStatsAdapter(activityList, activityEntryList)
        activitystats1_recyclerview.adapter = activityAdapter
        val activity_glm = GridLayoutManager(requireActivity().applicationContext, 4)
        activitystats1_recyclerview.layoutManager = activity_glm
        activitystats1_recyclerview.setHasFixedSize(true)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}