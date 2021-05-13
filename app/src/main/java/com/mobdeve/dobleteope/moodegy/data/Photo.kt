package com.mobdeve.dobleteope.moodegy.data

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey val id: Int,

    val photo: Bitmap
)

data class MoodEntryAndPhoto(
    @Embedded val moodEntry: MoodEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val photo: String
)
