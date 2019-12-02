package com.android.photosapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.photosapp.data.dao.PhotoDao
import com.android.photosapp.data.entity.PhotoEntity

@Database(entities = arrayOf(PhotoEntity::class), exportSchema = false, version = 4)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photosDao(): PhotoDao

    companion object {
        @Volatile
        var photoDatabase: PhotoDatabase? = null

        operator fun invoke(application: Application) = photoDatabase ?: synchronized(this) {
            photoDatabase ?: buildDatabase(application).also { photoDatabase = it }
        }


        fun buildDatabase(application: Application): PhotoDatabase = Room.databaseBuilder(
            application,
            PhotoDatabase::class.java,
            "photos_database"
        ).fallbackToDestructiveMigration().build()
    }
}