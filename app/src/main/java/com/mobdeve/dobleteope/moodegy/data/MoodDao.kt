package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*

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
    fun getActivity(id: Int): Mood
}