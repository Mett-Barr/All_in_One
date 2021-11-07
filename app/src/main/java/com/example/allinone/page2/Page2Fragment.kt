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

    private lateinit var toast: Toast

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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.fragmentContainerView2.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
//                when (destination.id) {
//                    R.id.stopScheduleFragment -> {
//                        viewModel._goToState.value = 101
//                    }
//                    R.id.fullScheduleFragment ->
//                        if (viewModel._goToState.value != 10) viewModel._goToState.value = 10
//
//                }
//                if (destination.id == R.id.stopScheduleFragment) viewModel._goToState.value = 101
//                else if (destination.id == R.id.action_global_fullScheduleFragment) viewModel._goToState.value = 10


//                destination.

                getToast()
            }
    }

    private fun init() {
        goToState()
        backPress()

    }

    private fun goToState() {
        viewModel.goToState.observe(viewLifecycleOwner, Observer { state ->
            MainViewModel.apply {
                if (state != nowState() && level(state) != 2) navigation(ridFromState(state))

//                when (state) {
//                    PAGE_COMPONENTS -> navigation(R.id.action_global_componentsFragment)
//                    PAGE_NOTIFICATION -> navigation(R.id.action_global_notificationFragment)
//                    PAGE_SERVICE -> navigation(R.id.action_global_serviceFragment)
//                    PAGE_BROADCAST -> navigation(R.id.action_global_boardcastFragment)
//                    PAGE_VIBRATION -> navigation(R.id.action_global_vibrationFragment)
//                    PAGE_CONTENT_PROVIDER -> navigation(R.id.action_global_contentProviderFragment)
//                    PAGE_INTERNET -> navigation(R.id.action_global_internetFragment)
//                    PAGE_FULL_SCHEDULE -> {
//                        if (stateFromRid(getNavController().currentDestination?.id) != PAGE_FULL_SCHEDULE) {
//                            navigation(R.id.action_global_fullScheduleFragment)
//                        }
//                    }
//                }


            }
//            navigation(MainViewModel.stateToRid(it))
        })
    }

    private fun backPress() {
        viewModel.backControl.observe(viewLifecycleOwner, Observer {
//            binding.fragmentContainerView2.findNavController().popBackStack()
//            viewModel._goToState.value
            Log.d("!!!!", "backControl ")

//            viewModel._goToState.value =
//                binding.fragmentContainerView2.findNavController().currentDestination!!.id

            when (
                MainViewModel.level(
                    MainViewModel.stateFromRid(
                        binding.fragmentContainerView2.findNavController().currentDestination?.id
                    )
                )) {
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

    private fun navigation(i: Int) = binding.fragmentContainerView2.findNavController().navigate(i)

    private fun nowState(): Int? = getNavController().currentDestination?.id

    private fun getNavController(): NavController =
        binding.fragmentContainerView2.findNavController()


    private fun getToast() {
        if (this::toast.isInitialized) {
            toast.cancel()
        }
        toast = Toast.makeText(
            context,
            viewModel._goToState.value.toString(),
            Toast.LENGTH_SHORT
        )
//        toast.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Page2Fragment()
    }
}