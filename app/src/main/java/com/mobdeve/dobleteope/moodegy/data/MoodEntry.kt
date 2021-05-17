package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*
import java.sql.Date

@Entity(tableName = "mood_entry",
    foreignKeys =
        [ForeignKey(
            entity = Mood::class,
            parentColumns = ["id"],
            childColumns = ["moodID"],
            onDelete = ForeignKey.CASCADE)
        ])
data class MoodEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dateTime: String,
    @ColumnInfo(index = true)
    val moodID: Int
)

//data class AllActivitiesInMoodEntry(
//    @Embedded val activityEntry: ActivityEntry,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "moodEntryID"
//    )
//    val entries: List<ActivityEntry>
//)
