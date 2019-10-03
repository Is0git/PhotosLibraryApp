package com.android.photosapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.photosapp.data.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo_table")
    fun getPhotos() : LiveData<List<PhotoEntity>>

    @Insert
    suspend fun addPhoto(photo:PhotoEntity)

    @Query("DELETE FROM photo_table")
    suspend fun deleteAll()
}