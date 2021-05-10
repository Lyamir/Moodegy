package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey val id: Int,
    val photo: String
)

data class MoodEntryAndPhoto(
    @Embedded val moodEntry: MoodEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val photo: String
)
