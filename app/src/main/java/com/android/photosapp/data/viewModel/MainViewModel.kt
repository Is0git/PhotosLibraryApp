package com.android.photosapp.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.data.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val mainRepository:MainRepository = MainRepository(application)

    public var photos = { mainRepository.getPhotos()}

    fun addPhoto(photo:PhotoEntity) =  viewModelScope.launch {mainRepository.addPhoto(photo)  }

     fun deleteAll() = viewModelScope.launch { mainRepository.deleteAll()}
}