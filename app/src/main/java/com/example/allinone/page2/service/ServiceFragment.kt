package com.example.allinone.page2.service

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.allinone.databinding.FragmentServiceBinding
import com.example.allinone.main.MainViewModel

class ServiceFragment : Fragment() {
    private lateinit var binding: FragmentServiceBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
        init()
        return binding.root
    }

    private fun init() {
        serviceInit()
        binderInit()
        mseesengerInit()
    }

    private fun serviceInit() {
        var boo = true
        val intent = Intent(activity, Service::class.java)
        binding.serviceButton.setOnClickListener {
            boo = if (boo) {
                activity?.startService(intent)
                binding.serviceButton.text = "stop"
                !boo
            } else {
                activity?.stopService(intent)
                binding.serviceButton.text = "start"
                Service().stopTimer()
//                context?.let { it1 -> Service().cancelNoti(it1) }
                !boo
            }
        }
    }

    private fun binderInit() {
    }

    private fun mseesengerInit() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = ServiceFragment()
    }
}