package com.example.allinone.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.allinone.R
import com.example.allinone.databinding.FragmentBlankBinding

// TODO: Rename parameter arguments, choose names that match
class BlankFragment : Fragment() {
    lateinit var NavController: NavController
    lateinit var BF2: BlankFragment2

    lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
//        init()
        return binding.root
    }

//    private fun init() {
//        binding.button.setOnClickListener {
//            BF2.binding.fragmentContainerView.findNavController().navigate(R.id.action_global_blankFragment4)
//        }
//    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BlankFragment().apply {
            }
    }
}