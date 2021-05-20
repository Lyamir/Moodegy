package com.mobdeve.dobleteope.moodegy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mobdeve.dobleteope.moodegy.fragments.HomeFragment
import com.mobdeve.dobleteope.moodegy.fragments.SettingsFragment
import com.mobdeve.dobleteope.moodegy.fragments.StatsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val statsFragment = StatsFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_stats -> makeCurrentFragment(statsFragment)
                R.id.ic_settings -> makeCurrentFragment(settingsFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}