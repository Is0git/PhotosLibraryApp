package com.android.photosapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

object ChannelConst {
    const val photoTaskChannel: String = "PHOTO_TASK_CHANNEL"
}

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        setNotificationChannels()

    }

    fun setNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager: NotificationManager? = getSystemService(NotificationManager::class.java)
            val photoTaskChannel: NotificationChannel = NotificationChannel(
                ChannelConst.photoTaskChannel,
                "Task with photos channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                name = "PHOTO TASK CHANNEL"
                description = "Uploaded image notifications"
            }
            manager?.createNotificationChannel(photoTaskChannel)
        }

    }
}