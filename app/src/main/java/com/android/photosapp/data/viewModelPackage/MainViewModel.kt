package com.android.photosapp.data.viewModelPackage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.data.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val mainRepository: MainRepository = MainRepository(application)

    var photo: LiveData<MutableList<PhotoEntity>> = mainRepository.getPhotos()

    fun addPhoto(photo: PhotoEntity) = viewModelScope.launch { mainRepository.addPhoto(photo) }

    fun deleteAll() = viewModelScope.launch { mainRepository.deleteAll() }

    fun deletePhoto(photo: PhotoEntity) =
        viewModelScope.launch { mainRepository.deletePhoto(photo) }
}