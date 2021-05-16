package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*

@Dao
interface ActivityDao {
    @Insert
    fun insert(vararg activity: Activity)

    @Delete
    fun delete(vararg activity: Activity)

    @Update
    fun update(vararg activity: Activity)

    @Query("SELECT * FROM activity")
    fun getAll(): List<Activity>

    @Query("SELECT * FROM activity WHERE id = :id")
    fun getActivity(id: Int): Activity
}