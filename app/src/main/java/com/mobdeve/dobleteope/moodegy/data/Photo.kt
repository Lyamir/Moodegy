package com.mobdeve.dobleteope.moodegy.data

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "photo",
    foreignKeys =
    [ForeignKey(
        entity = MoodEntry::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE)
    ])
data class Photo(
    @PrimaryKey
    val id: Int,
    val photo: Bitmap
)

