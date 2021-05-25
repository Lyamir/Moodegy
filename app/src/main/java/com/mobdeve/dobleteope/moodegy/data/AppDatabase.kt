package com.mobdeve.dobleteope.moodegy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mobdeve.dobleteope.moodegy.data.daos.*

@Database(
    entities = [
        MoodEntry::class,
        Activity::class,
        ActivityEntry::class,
        Mood::class,
        Description::class,
        Photo::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters (Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moodEntryDao(): MoodEntryDao
    abstract fun activityEntryDao(): ActivityEntryDao
    abstract fun activityDao(): ActivityDao
    abstract fun moodDao(): MoodDao
    abstract fun photoDao(): PhotoDao
    abstract fun descDao(): DescriptionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MoodegyDB"
                ).allowMainThreadQueries().addCallback(rdc).build()
                INSTANCE = instance
                return instance
            }
        }

        private val rdc = object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO mood ('name') VALUES ('Happy')")
                db.execSQL("INSERT INTO mood ('name') VALUES ('Angry')")
                db.execSQL("INSERT INTO mood ('name') VALUES ('Sad')")
                db.execSQL("INSERT INTO mood ('name') VALUES ('Okay')")
                db.execSQL("INSERT INTO activity ('name') VALUES ('Family')")
                db.execSQL("INSERT INTO activity ('name') VALUES ('Relationship')")
                db.execSQL("INSERT INTO activity ('name') VALUES ('Sports')")
                db.execSQL("INSERT INTO activity ('name') VALUES ('School')")
                db.execSQL("INSERT INTO activity ('name') VALUES ('Work')")
                db.execSQL("INSERT INTO activity ('name') VALUES ('Hobbies')")
            }
        }
    }

}