package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "activity_entry", primaryKeys = ["moodEntryID", "activityID"])
data class ActivityEntry(
    val moodEntryID: Int,
    val activityID: Int
)

