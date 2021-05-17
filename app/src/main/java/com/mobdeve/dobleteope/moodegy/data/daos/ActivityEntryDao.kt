package com.mobdeve.dobleteope.moodegy.data.daos

import androidx.room.*
import com.mobdeve.dobleteope.moodegy.data.ActivityEntry

@Dao
interface ActivityEntryDao {
    @Query("SELECT * FROM activity_entry")
    fun getAll(): List<ActivityEntry>
}