package com.android.photosapp.dataBinding

//import com.bumptech.glide.request.RequestListener
//import com.bumptech.glide.request.target.SimpleTarget
//import com.bumptech.glide.request.target.Target
//import java.io.File
//import com.bumptech.glide.request.RequestOptions
//import android.R.attr.fragment
//import android.R
//
//
//
//
//
//@BindingAdapter("app:uploadImage", "app:setProgressBar")
//fun upload(view:View, url:String, progressBar: ProgressBar) {
//    progressBar.visibility = View.VISIBLE
//
//    Glide.with(view.context)
//        .load(url.toUri())
//        .centerCrop()
//        .listener(object : RequestListener<Drawable> {
//            override fun onLoadFailed(
//                e: GlideException?,
//                model: Any?,
//                target: Target<Drawable>?,
//                isFirstResource: Boolean
//            ): Boolean {
//                progressBar.visibility = View.INVISIBLE
//                return false
//            }
//
//
//            override fun onResourceReady(
//                resource: Drawable?,
//                model: Any?,
//                target: Target<Drawable>?,
//                dataSource: DataSource?,
//                isFirstResource: Boolean
//            ): Boolean {
//                progressBar.visibility = View.INVISIBLE
//                return false
//            }
//        }).into(view)
//
//}
//

