package com.android.photosapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.photosapp.data.viewModelPackage.MainViewModel
import com.android.photosapp.databinding.MainFragmentLayoutBinding
import com.android.photosapp.ui.adapters.AllPhotosAdapter

class MainFragment : Fragment() {
    lateinit var mainFragmentBinding: MainFragmentLayoutBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: AllPhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding = MainFragmentLayoutBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mainFragmentBinding.apply {
            lifecycleOwner = activity
            model = viewModel
        }
        adapter = AllPhotosAdapter()
        mainFragmentBinding.recyclerView.adapter = adapter
        viewModel.photo.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (adapter.currentList[viewHolder.adapterPosition] != null) {
                    viewModel.deletePhoto(adapter.currentList[viewHolder.adapterPosition])
                }
            }
        }).attachToRecyclerView(mainFragmentBinding.recyclerView)

        return mainFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        mainFragmentBinding.invalidateAll()
    }
}
