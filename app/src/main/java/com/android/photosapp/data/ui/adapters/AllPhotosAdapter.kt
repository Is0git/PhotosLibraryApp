package com.android.photosapp.data.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.databinding.PhotosAdapterLayoutBinding

class AllPhotosAdapter :
    ListAdapter<PhotoEntity, AllPhotosAdapter.MyViewHolder>(callback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding:PhotosAdapterLayoutBinding = PhotosAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.setPhoto(item)
    }



    class MyViewHolder(val binding: PhotosAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}

var callback: DiffUtil.ItemCallback<PhotoEntity> = object :
    DiffUtil.ItemCallback<PhotoEntity>() {
    override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
        return oldItem == newItem
    }
}