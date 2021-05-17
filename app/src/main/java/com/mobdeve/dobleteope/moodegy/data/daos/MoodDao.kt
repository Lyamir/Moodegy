package com.mobdeve.dobleteope.moodegy.data.daos

import androidx.room.*
import com.mobdeve.dobleteope.moodegy.data.Mood

@Dao
interface MoodDao {
    @Insert
    fun insert(vararg mood: Mood)

    @Delete
    fun delete(vararg mood: Mood)

    @Update
    fun update(vararg mood: Mood)

    @Query("SELECT * FROM mood")
    fun getAll(): List<Mood>

    @Query("SELECT * FROM mood WHERE id = :id")
    fun getMood(id: Int): Mood
}