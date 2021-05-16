package com.mobdeve.dobleteope.moodegy.data

import androidx.room.*

@Dao
interface PhotoDao {
    @Insert
    fun insert(vararg photo: Photo)

    @Delete
    fun delete(vararg photo: Photo)

    @Update
    fun update(vararg photo: Photo)

    @Query("SELECT * FROM photo WHERE id = :id")
    fun getActivity(id: Int): Photo
}