package com.example.allinone.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allinone.R
import com.example.allinone.databinding.FragmentMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()

        binding.let {
            it.lifecycleOwner
        }

        return binding.root

    }

    private fun init() {
        recyclerView()
    }

    private fun recyclerView() {
        binding.rv.apply {
            adapter = MainAdapter(
                resources.getStringArray(R.array.main_item),
                viewModel,
                this@MainFragment
            )
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    fun toast() {
        activity?.let { ApplicationToast.showToast(it, "Toast!") }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}