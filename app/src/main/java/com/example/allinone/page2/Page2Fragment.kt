package com.example.allinone.page2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.allinone.databinding.FragmentPage2Binding
import com.example.allinone.main.MainViewModel

class Page2Fragment : Fragment() {

    private lateinit var binding: FragmentPage2Binding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPage2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()
        return binding.root
    }


    private fun init() {
        goToState()
        backPress()
    }

    private fun goToState() {
        viewModel.goToState.observe(viewLifecycleOwner, Observer { state ->
            MainViewModel.apply {
                if (state != nowState() && level(state) != 2) navigation(ridFromState(state))
            }
            Log.d("!!!", "goToState: $state")
        })
    }

    private fun backPress() {
        viewModel.backControl.observe(viewLifecycleOwner, Observer {
            when (MainViewModel.level(MainViewModel.stateFromRid(nowState()))) {
                1 -> viewModel.goToPage1()
                2 -> {
                    binding.fragmentContainerView2.findNavController().apply {
                        popBackStack()
                        viewModel._goToState.value =
                            MainViewModel.stateFromRid(this.currentDestination?.id)
                    }
                }
            }
        })
    }

    private fun navigation(i: Int) = getNavController().navigate(i)

    private fun nowState(): Int? = getNavController().currentDestination?.id

    private fun getNavController(): NavController =
        binding.fragmentContainerView2.findNavController()

    companion object {
        @JvmStatic
        fun newInstance() = Page2Fragment()
    }
}