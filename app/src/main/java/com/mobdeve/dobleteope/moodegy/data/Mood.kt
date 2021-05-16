package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "mood")
data class Mood(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
