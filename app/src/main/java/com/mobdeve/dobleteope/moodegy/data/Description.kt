package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Blob

@Entity(tableName = "description")
data class Description(
    @PrimaryKey val id: Int,
    val text: String
)

data class MoodEntryAndDescription(
    @Embedded val moodEntry: MoodEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val description: Description
)
