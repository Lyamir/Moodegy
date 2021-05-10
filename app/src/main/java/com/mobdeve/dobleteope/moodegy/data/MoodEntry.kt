package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date

@Entity(tableName = "mood_entry")
data class MoodEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dateTime: String,
    val moodID: Int
)

//data class AllActivitiesInMoodEntry(
//    @Embedded val activityEntry: ActivityEntry,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "moodEntryID"
//    )
//    val entries: List<ActivityEntry>
//)
