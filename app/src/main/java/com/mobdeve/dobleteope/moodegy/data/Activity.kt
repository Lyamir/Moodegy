package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "activity")
data class Activity(
    @PrimaryKey val id: Int,
    val name: String
)

data class AllActivitiesInAnActivityEntry(
    @Embedded val activityEntry: ActivityEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "activityID"
    )
    val entries: List<ActivityEntry>
)
