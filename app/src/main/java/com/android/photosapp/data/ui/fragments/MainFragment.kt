package com.android.photosapp.data.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.data.ui.adapters.AllPhotosAdapter
import com.android.photosapp.data.viewModel.MainViewModel
import com.android.photosapp.databinding.MainFragmentLayoutBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photos_adapter_layout.*

class MainFragment : Fragment() {
    lateinit var mainFragmentBinding: MainFragmentLayoutBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter:AllPhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding = MainFragmentLayoutBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        Log.d("TAG1", "START")

        adapter = AllPhotosAdapter()
        mainFragmentBinding.recyclerView.adapter = adapter
        viewModel.photos().observe(viewLifecycleOwner, Observer {
            Log.d("TAG1", "OBSE")
            adapter.setData(it) })

        return mainFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG1", "RESUME")
        mainFragmentBinding.invalidateAll()
    }
}