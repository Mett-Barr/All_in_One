package com.example.allinone.page2.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.allinone.databinding.FragmentRoomBinding
import com.example.allinone.main.MainApplication
import kotlinx.coroutines.launch

//import kotlinx.coroutines.launch

class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: ScheduleViewModel by viewModels() {
        ScheduleViewModelFactory(
            (activity?.application as MainApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val busStopAdapter = RoomAdapter {
            val action = RoomFragmentDirections
                .actionDBFragmentToSubRoomFragment(
                    stopName = it.stopName
                )
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = busStopAdapter

        lifecycle.coroutineScope.launch {
            viewModel.roomSchedule().collect(){
                busStopAdapter.s
            }
        }
    }

//    companion object {
//        @JvmStatic
//        fun newInstance() = RoomFragment()
//    }

    override fun onDestroyView() {
        super.onDestroyView(); _binding = null
    }

}