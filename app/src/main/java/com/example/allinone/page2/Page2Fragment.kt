package com.example.allinone.page2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.allinone.R
import com.example.allinone.databinding.FragmentBlank2Binding
import com.example.allinone.databinding.FragmentPage2Binding
import com.example.allinone.main.MainViewModel

class Page2Fragment : Fragment() {
    private lateinit var binding: FragmentPage2Binding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPage2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()
        return binding.root
    }

    private fun init() {
        viewModel.goToState.observe(viewLifecycleOwner, Observer {
            when (it) {
                0 -> binding.fragmentContainerView2.findNavController()
                    .navigate(R.id.action_global_componentsFragment)
                1 -> binding.fragmentContainerView2.findNavController()
                    .navigate(R.id.action_global_blankFragment4)
                2 -> binding.fragmentContainerView2.findNavController()
                    .navigate(R.id.action_global_notificationFragment)
            }
        })

        viewModel.goToState.observe(viewLifecycleOwner, Observer {
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Page2Fragment()
    }

}