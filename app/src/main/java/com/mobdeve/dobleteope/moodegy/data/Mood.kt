package com.mobdeve.dobleteope.moodegy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "mood")
data class Mood(
    @PrimaryKey val id: Int,
    val name: String
)

data class AllMoodEntriesWithMood(
    @Embedded val mood: Mood,
    @Relation(
        parentColumn = "id",
        entityColumn = "moodID"
    )
    val entries: List<MoodEntry>
)