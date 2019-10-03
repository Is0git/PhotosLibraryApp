package com.android.photosapp.data.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.databinding.PhotosAdapterLayoutBinding

class AllPhotosAdapter : RecyclerView.Adapter<AllPhotosAdapter.MyViewHolder>() {
    var photos:List<PhotoEntity>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding:PhotosAdapterLayoutBinding = PhotosAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = photos?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = photos?.get(position)
        holder.binding.setPhoto(item)
    }

    fun setData(items:List<PhotoEntity>) {
        this.photos = items
    notifyDataSetChanged()}

    class MyViewHolder(val binding: PhotosAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}