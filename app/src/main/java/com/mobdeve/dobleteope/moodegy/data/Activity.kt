package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "activity")
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
