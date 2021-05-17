package com.mobdeve.dobleteope.moodegy.data.daos

import androidx.room.*
import com.mobdeve.dobleteope.moodegy.data.Description

@Dao
interface DescriptionDao {
    @Insert
    fun insert(vararg description: Description)

    @Delete
    fun delete(vararg description: Description)

    @Update
    fun update(vararg description: Description)

    @Query("SELECT * FROM description WHERE id = :id")
    fun getActivity(id: Int): Description
}