package com.android.photosapp.data.services

import android.app.IntentService
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.android.photosapp.ChannelConst
import com.android.photosapp.MainActivity
import com.android.photosapp.R
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.data.repositories.MainRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UploadService : IntentService("photo_service_thread") {
    lateinit var repository: MainRepository
    lateinit var notificationCombat: NotificationManagerCompat
    lateinit var photoNotification: Notification
    private val job: Job = Job()

    override fun onCreate() {
        super.onCreate()
        repository = MainRepository(application)
        notificationCombat = NotificationManagerCompat.from(this)

    }

    override fun onHandleIntent(p0: Intent?) {
        Log.d("TAG1", "START3")
        if (p0 != null && p0.hasExtra("upload_photo")) {
            val photo: PhotoEntity = p0.getParcelableExtra("upload_photo")
            runBlocking {
                repository.addPhoto(photo)
                runNotification(photo)
            }

        }
    }

    fun runNotification(photo: PhotoEntity) {
        val intent:Intent = Intent()
        intent.setData(photo.image_url?.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent:PendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        photoNotification = Notification.Builder(applicationContext, ChannelConst.photoTaskChannel)
            .setContentTitle("Image is uploaded with title ${photo.title}")
            .setContentText("Description: ${photo.description}")
            .setSmallIcon(R.drawable.ic_add_on_secondary_24dp)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationCombat.notify(1, photoNotification)

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}