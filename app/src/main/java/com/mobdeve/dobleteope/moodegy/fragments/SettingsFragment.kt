package com.mobdeve.dobleteope.moodegy.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SwitchCompat
import com.mobdeve.dobleteope.moodegy.NotificationReceiver
import com.mobdeve.dobleteope.moodegy.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_settings, container, false)
        val timepicker: TimePicker = view.findViewById(R.id.settime_timepicker)
        val switch: SwitchCompat = view.findViewById(R.id.notification_switch)
        val setTime: Button = view.findViewById(R.id.setnotification_btn)


        createNotificationChannel()
        timepicker.isEnabled = true

        val intent: Intent = Intent(requireContext(), NotificationReceiver::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        val alarmManager: AlarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        switch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) run {
                setTime.isEnabled = true
                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, timepicker.hour)
                    set(Calendar.MINUTE, timepicker.minute)
                    set(Calendar.SECOND, 0)
                }

                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent)
            }
            else run{
                alarmManager.cancel(pendingIntent)
            }
        }

        setTime.setOnClickListener {
            alarmManager.cancel(pendingIntent)
            val calendar: Calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, timepicker.hour)
                set(Calendar.MINUTE, timepicker.minute)
                set(Calendar.SECOND, 0)
            }

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent)
        }


        return view

    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "Notification Reminder Channel"
            val description: String = "Channel for Notification Reminder"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel(
                "com.mobdeve.dobleteope.moodegy.notification_receiver", name, importance)

            val notifManager: NotificationManager = requireContext().getSystemService(NotificationManager::class.java)
            notifManager.createNotificationChannel(channel)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}