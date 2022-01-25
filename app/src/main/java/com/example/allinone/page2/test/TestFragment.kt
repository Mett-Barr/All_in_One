package com.example.allinone.page2.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.allinone.databinding.FragmentTestBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: TestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        uiInit()
        return binding.root
    }

    private fun uiInit() {
        lifecycleScope.launch {
            while (true) {
                delay(800)
                viewModel.new()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TestFragment()
    }
}