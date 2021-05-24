package com.mobdeve.dobleteope.moodegy.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobdeve.dobleteope.moodegy.*
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.btn_addactivity
import kotlinx.android.synthetic.main.fragment_home.btn_addmoodentry
import kotlinx.android.synthetic.main.fragment_home.main_recyclerview

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), MoodEntryAdapter.OnMoodEntryClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var moodEntryList: List<MoodEntry>? = null

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
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val btn_addmood : Button = view.findViewById(R.id.btn_addmood)
        val btn_addactivity : Button = view.findViewById(R.id.btn_addactivity)
        val btn_addmoodentry : Button = view.findViewById(R.id.btn_addmoodentry)

        btn_addmood.setOnClickListener { view ->
            val intent = Intent(activity, AddMoodActivity::class.java)
            startActivity(intent)
        }

        btn_addactivity.setOnClickListener { view ->
            val intent = Intent(activity, AddActivityActivity::class.java)
            startActivity(intent)
        }

        btn_addmoodentry?.setOnClickListener{ view ->
            val intent = Intent(activity, AddMoodEntryActivity::class.java)
            startActivity(intent)
        }

        return view



    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getDatabase(requireActivity().applicationContext)
        val moodEntryDao = db.moodEntryDao()
        val moodDao = db.moodDao()
        val activityDao = db.activityDao()
        val activityEntryDao = db.activityEntryDao()

        val moodList = moodDao.getAll()
        moodEntryList = moodEntryDao.getAll()
        val activityList = activityDao.getAll()
        val activityEntryList = activityEntryDao.getAll()
        val adapter = MoodEntryAdapter(moodEntryList as List<MoodEntry>, moodList, activityList, activityEntryList, this)
        main_recyclerview.adapter = adapter
        val llm = LinearLayoutManager(activity)
        llm.reverseLayout = true
        llm.stackFromEnd = true
        main_recyclerview.layoutManager = llm
        main_recyclerview.setHasFixedSize(true)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMoodEntryClick(position: Int) {
        val intent = Intent(activity, ViewMoodEntry::class.java)
        intent.putExtra("moodEntry", Gson().toJson(moodEntryList?.get(position)))
        startActivity(intent)
    }
}