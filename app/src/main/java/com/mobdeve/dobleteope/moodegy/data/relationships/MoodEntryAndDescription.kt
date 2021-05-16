package com.mobdeve.dobleteope.moodegy.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.mobdeve.dobleteope.moodegy.data.Description
import com.mobdeve.dobleteope.moodegy.data.MoodEntry

data class MoodEntryAndDescription(
    @Embedded val moodEntry: MoodEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val description: Description
)
