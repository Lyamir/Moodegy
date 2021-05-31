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
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.mobdeve.dobleteope.moodegy.NotificationReceiver
import com.mobdeve.dobleteope.moodegy.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                Log.d("uwu", isChecked.toString())
                Log.d("uwu", timepicker.hour.toString())
                Log.d("uwu", timepicker.minute.toString())
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

            Toast.makeText(activity, "Notification Time set!", Toast.LENGTH_SHORT).show()
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

}