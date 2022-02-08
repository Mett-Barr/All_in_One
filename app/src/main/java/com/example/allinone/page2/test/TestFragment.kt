package com.example.allinone.page2.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.allinone.databinding.FragmentTestBinding
import com.example.allinone.page2.room.BusScheduleApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding

    //    private val viewModel: TestViewModel by viewModels()
    private val viewModel: TestViewModel by viewModels {
        TestViewModelFactory(
            (activity?.application as BusScheduleApplication).testDatabase.testItemDao()
        )
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (viewModel.testRoomIsEmpty()) {
//            viewModel.newTestItem()
//        }
        lifecycleScope.launch {

        }
        viewModel.testRoomEmptyCheck()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TestFragment()
    }
}