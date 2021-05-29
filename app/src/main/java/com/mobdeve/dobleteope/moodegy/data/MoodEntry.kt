package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*
import java.sql.Date

@Entity(tableName = "mood_entry")
data class MoodEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dateTime: String,
    val moodName: String
)
