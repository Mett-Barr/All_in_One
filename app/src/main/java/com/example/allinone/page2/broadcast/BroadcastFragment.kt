package com.example.allinone.page2.broadcast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.allinone.databinding.FragmentBroadcastBinding

class BroadcastFragment : Fragment() {
    private lateinit var binding: FragmentBroadcastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBroadcastBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    private fun init() {
        orderedBroadcast()
        broadcast()
        localBroadcastManager()
    }

    private fun orderedBroadcast() {
    }

    private fun broadcast() {
    }

    private fun localBroadcastManager() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = BroadcastFragment()
    }
}