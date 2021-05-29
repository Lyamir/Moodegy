package com.mobdeve.dobleteope.moodegy

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.mobdeve.dobleteope.moodegy.data.AppDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotificationReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        val resultIntent = Intent(context, AddMoodEntryActivity::class.java)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


         val builder: NotificationCompat.Builder = NotificationCompat.Builder(
             context,
             "com.mobdeve.dobleteope.moodegy.notification_receiver")
             .setSmallIcon(R.mipmap.ic_launcher_foreground)
             .setContentTitle("Mood Entry Reminder")
             .setContentText("You haven't added an entry for today! Add one now!")
             .setPriority(NotificationCompat.PRIORITY_HIGH)
             .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
             .setContentIntent(resultPendingIntent)
         val notifManager: NotificationManagerCompat = NotificationManagerCompat.from(context)

        val db = AppDatabase.getDatabase(context)
        val moodEntryDao = db.moodEntryDao()
        val moodEntryList = moodEntryDao.getAll()
        Log.d("uwu", "created~")

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
        val currentDate: String = current.format(formatter)
        Log.d("uwu currentDate", currentDate)
        var finder: Boolean = false
        for(mood in moodEntryList){
            val date = mood.dateTime.split(" ")[0]
            Log.d("uwu", date)
            if(date == currentDate){
                finder = true
                break
            }
        }

        if(!finder){
            notifManager.notify(200, builder.build())
        }

    }
}