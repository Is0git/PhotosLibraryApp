package com.android.photosapp.data.repositories

import android.app.Application
import com.android.photosapp.data.dao.PhotoDao
import com.android.photosapp.data.database.PhotoDatabase
import com.android.photosapp.data.entity.PhotoEntity

class MainRepository(application: Application) {
    private var photoDatabase: PhotoDatabase = PhotoDatabase(application)
    private var photoDao: PhotoDao = photoDatabase.photosDao()

    fun getPhotos() = photoDao.getPhotos()

    suspend fun addPhoto(photoEntity: PhotoEntity) = photoDao.addPhoto(photoEntity)

    suspend fun deleteAll() = photoDao.deleteAll()

    suspend fun deletePhoto(photo: PhotoEntity) = photoDao.deletePhoto(photo)

}