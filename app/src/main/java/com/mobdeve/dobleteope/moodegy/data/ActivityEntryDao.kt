package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*

@Dao
interface ActivityEntryDao {
    @Query("SELECT * FROM activity_entry")
    fun getAll(): List<ActivityEntry>
}