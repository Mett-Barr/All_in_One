package com.example.allinone.page2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.allinone.R
import com.example.allinone.databinding.FragmentPage2Binding
import com.example.allinone.main.MainViewModel

class Page2Fragment : Fragment() {
    private lateinit var binding: FragmentPage2Binding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPage2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()
        return binding.root
    }

    private fun init() {
        viewModel.goToState.observe(viewLifecycleOwner, Observer {
            when (it) {
                viewModel.PAGE_COMPONENTS -> navigation(R.id.action_global_componentsFragment)
                viewModel.PAGE_NOTIFICATION -> navigation(R.id.action_global_notificationFragment)
                viewModel.PAGE_SERVICE -> navigation(R.id.action_global_serviceFragment)
                viewModel.PAGE_BROADCAST -> navigation(R.id.action_global_boardcastFragment)
                viewModel.PAGE_VIBRATION -> navigation(R.id.action_global_vibrationFragment)
                viewModel.PAGE_CONTNET_PROVIDER -> navigation(R.id.action_global_contentProviderFragment)
            }
        })

        viewModel.goToState.observe(viewLifecycleOwner, Observer {
        })
    }

    private fun navigation(i: Int) {
        binding.fragmentContainerView2.findNavController().navigate(i)
    }

    companion object {
        @JvmStatic
        fun newInstance() = Page2Fragment()
    }
}