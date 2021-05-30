package com.mobdeve.dobleteope.moodegy.data.daos

import androidx.room.*
import com.mobdeve.dobleteope.moodegy.data.ActivityEntry

@Dao
interface ActivityEntryDao {

    @Insert
    fun insert(vararg activityEntry: ActivityEntry)

    @Update
    fun update(vararg activityEntry: ActivityEntry)

    @Query("SELECT * FROM activity_entry")
    fun getAll(): List<ActivityEntry>

    @Query("SELECT * FROM activity_entry WHERE moodEntryID = :moodEntryID")
    fun getEntryFromMoodID(moodEntryID: Int): ActivityEntry
    
}