package com.mobdeve.dobleteope.moodegy.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.mobdeve.dobleteope.moodegy.data.MoodEntry
import com.mobdeve.dobleteope.moodegy.data.Photo

data class MoodEntryAndPhoto(
    @Embedded val moodEntry: MoodEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val photo: Photo
)
