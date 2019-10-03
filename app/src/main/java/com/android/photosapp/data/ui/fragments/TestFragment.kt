package com.android.photosapp.data.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.photosapp.databinding.TestBinding

class TestFragment : Fragment() {
        lateinit var binding:TestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TestBinding.inflate(inflater, container, false)
        return binding.root
    }
}