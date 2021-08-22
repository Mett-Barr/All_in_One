package com.example.allinone.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.allinone.R
import com.example.allinone.databinding.FragmentBlank2Binding

class BlankFragment2 : Fragment() {
    lateinit var BF1: BlankFragment

    lateinit var binding: FragmentBlank2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlank2Binding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        BF1.binding.button.setOnClickListener {
            binding.fragmentContainerView.findNavController().navigate(R.id.action_global_blankFragment4)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BlankFragment2().apply {
            }
    }
}