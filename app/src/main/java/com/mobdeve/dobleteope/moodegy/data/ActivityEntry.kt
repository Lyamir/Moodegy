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
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = Activity::class,
            parentColumns = ["id"],
            childColumns = ["activityID"],
            onDelete = ForeignKey.CASCADE)
        ])
data class ActivityEntry(
    val moodEntryID: Int,
    val activityID: Int
)

