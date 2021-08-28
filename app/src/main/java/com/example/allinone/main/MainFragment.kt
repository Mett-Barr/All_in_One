package com.example.allinone.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.allinone.R
import com.example.allinone.databinding.FragmentMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()

    private val c = Calendar.getInstance()
    private val df = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    private val tf = SimpleDateFormat("hh:mm", Locale.getDefault())
    private val hf = SimpleDateFormat("hh", Locale.getDefault())
    private val mf = SimpleDateFormat("mm", Locale.getDefault())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()
        return binding.root
    }

    private fun init() {
        toast()
        component()
        date()
        test()
        clickable()
    }

    private fun clickable() {
        viewModel.pagerClickable.observe(viewLifecycleOwner, Observer {
            binding.CL.isClickable = it
        })
    }

    private fun date() {
        binding.cv3.text = df.format(c.time)
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select dates")
                .build()


        binding.datePickerCV3.setOnClickListener {
            datePicker.show(this.childFragmentManager, "")
        }

        datePicker.addOnPositiveButtonClickListener {
            c.timeInMillis = it
            binding.cv3.text = df.format(c.time)
        }
    }

    private fun test() {
    }

    private fun toast() {
        binding.toastCV1.setOnClickListener {
            Toast.makeText(activity, "Toast!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun component() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}