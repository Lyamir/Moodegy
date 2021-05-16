package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*
import com.mobdeve.dobleteope.moodegy.data.relationships.MoodEntryAndDescription
import com.mobdeve.dobleteope.moodegy.data.relationships.MoodEntryAndPhoto

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

    @Query("SELECT * FROM mood_entry WHERE id = :id")
    fun getMoodEntry(id: Int): MoodEntry

}