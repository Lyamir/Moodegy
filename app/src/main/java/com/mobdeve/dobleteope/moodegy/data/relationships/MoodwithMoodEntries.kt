package com.mobdeve.dobleteope.moodegy.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.mobdeve.dobleteope.moodegy.data.Mood
import com.mobdeve.dobleteope.moodegy.data.MoodEntry

data class MoodwithMoodEntries(
    @Embedded
    val mood: Mood,
    @Relation(
        parentColumn = "id",
        entityColumn = "moodID"
    )
    val entries: List<MoodEntry>
)
