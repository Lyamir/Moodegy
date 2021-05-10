package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Database
import androidx.room.RoomDatabase

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
abstract class AppDatabase: RoomDatabase() {
    abstract fun moodEntryDao(): MoodEntryDao
}