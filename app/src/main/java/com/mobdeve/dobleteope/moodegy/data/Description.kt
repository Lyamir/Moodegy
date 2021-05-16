package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*
import java.sql.Blob

@Entity(tableName = "description",
        foreignKeys =
            [ForeignKey(
                entity = MoodEntry::class,
                parentColumns = ["id"],
                childColumns = ["id"],
                onDelete = ForeignKey.CASCADE)
            ])
data class Description(
    @PrimaryKey val id: Int,
    val text: String
)

