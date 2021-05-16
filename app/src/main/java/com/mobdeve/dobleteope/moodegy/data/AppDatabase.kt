package com.mobdeve.dobleteope.moodegy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        MoodEntry::class,
        Activity::class,
        ActivityEntry::class,
        Mood::class,
        Description::class,
        Photo::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters (Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moodEntryDao(): MoodEntryDao
    abstract fun activityEntryDao(): ActivityEntryDao
    abstract fun activityDao(): ActivityDao
    abstract fun moodDao(): MoodDao
    abstract fun photoDao(): PhotoDao
    abstract fun descDao(): DescriptionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MoodegyDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}