package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*

@Entity(
    tableName = "activity_entry",
    primaryKeys = ["moodEntryID", "activityID"],
    foreignKeys =
        [ForeignKey(
            entity = MoodEntry::class,
            parentColumns = ["id"],
            childColumns = ["moodEntryID"],
            onDelete = ForeignKey.CASCADE)])
data class ActivityEntry(
    @ColumnInfo(index = true)
    val moodEntryID: Int,
    @ColumnInfo(index = true)
    val activityID: Int,
    val activityName: String
)

