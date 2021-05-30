package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*
import java.sql.Blob

@Entity(tableName = "description",
    primaryKeys = ["id"],
        foreignKeys =
            [ForeignKey(
                entity = MoodEntry::class,
                parentColumns = ["id"],
                childColumns = ["id"],
                onDelete = ForeignKey.CASCADE)
            ])
data class Description(
    val id: Int,
    val text: String
)

