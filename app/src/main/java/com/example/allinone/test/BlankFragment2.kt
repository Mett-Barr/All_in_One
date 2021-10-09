package com.example.allinone.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.allinone.R
import com.example.allinone.databinding.FragmentBlank2Binding
import com.example.allinone.main.MainViewModel

class BlankFragment2 : Fragment() {
    lateinit var BF1: BlankFragment

    lateinit var binding: FragmentBlank2Binding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlank2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        var i = 0
//        BF1.binding.button.setOnClickListener {
//            when (i) {
//                0 -> {
//                    binding.fragmentContainerView.findNavController()
//                        .navigate(R.id.action_global_blankFragment4)
//                    i = 1
//                }
//
//                1 -> {
//                    binding.fragmentContainerView.findNavController()
//                        .navigate(R.id.action_global_blankFragment3)
//                    i = 0
//                }
//            }
////            binding.fragmentContainerView.findNavController()
////                .navigate(R.id.action_global_blankFragment4)
//            BF1.viewPager2.currentItem = 1
//        }

        viewModel.goToState.observe(viewLifecycleOwner, Observer {
//            Log.d("TAG", "init: ")
//            Toast.makeText(activity, "observer $it", Toast.LENGTH_SHORT).show()
            when (it) {
                0 -> goToB3()
                1 -> goToB4()
            }
            Log.d("TAG", "BF2 Observer: $it")
        })

        viewModel.goToState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                0 -> {
                    goToB3()
//                    Toast.makeText(activity, "androidx.lifecycle.Observer $it", Toast.LENGTH_SHORT)
//                        .show()
//                    viewModel.goToB4()
                }
                1 -> {
                    goToB4()
//                    Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
//                    viewModel.goToB3()
                }
            }
            Log.d("TAG", "BF2 androidx.lifecycle.Observer: $it")
        })

        binding.button3.setOnClickListener {
            when (viewModel.goToState.value) {
//                0 -> viewModel.goToB4()
                1 -> viewModel.goToComponents()
            }
//            Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun goToB3() {
        binding.fragmentContainerView.findNavController()
            .navigate(R.id.action_global_blankFragment3)
    }

    fun goToB4() {
        binding.fragmentContainerView.findNavController()
            .navigate(R.id.action_global_blankFragment4)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BlankFragment2().apply {
            }
    }
}