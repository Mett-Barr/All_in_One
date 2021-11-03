package com.example.allinone.page2.internet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.allinone.databinding.FragmentInternetBinding

class InternetFragment : Fragment() {
    private lateinit var binding: FragmentInternetBinding
    private val viewModel: InternetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInternetBinding.inflate(inflater, container,  false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.photoGrid.adapter = PhotoGridAdapter()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = InternetFragment()
    }
}