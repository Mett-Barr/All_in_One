package com.example.allinone.page2.vibration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.allinone.R
import com.example.allinone.databinding.FragmentVibrationBinding

class VibrationFragment : Fragment() {
    private lateinit var binding: FragmentVibrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVibrationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        init()

        return binding.root
    }

    private fun init() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = VibrationFragment()
    }
}