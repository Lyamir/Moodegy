package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*

@Dao
interface MoodEntryDao {
    @Insert
    fun insert(vararg moodEntry: MoodEntry)

    @Delete
    fun delete(vararg moodEntry: MoodEntry)

    @Update
    fun update(vararg moodEntry: MoodEntry)

    @Query("SELECT * FROM mood_entry")
    fun getAll(): List<MoodEntry>

}