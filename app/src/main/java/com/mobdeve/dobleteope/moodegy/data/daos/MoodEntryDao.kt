package com.mobdeve.dobleteope.moodegy.data.daos

import androidx.room.*
import com.mobdeve.dobleteope.moodegy.data.MoodEntry

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

    @Query("SELECT * FROM mood_entry WHERE id = :id ORDER BY dateTime DESC")
    fun getMoodEntry(id: Int): MoodEntry

    @Query("SELECT * FROM mood_entry WHERE dateTime = :dateTime")
    fun getMoodEntryFromDate(dateTime: String): MoodEntry

}