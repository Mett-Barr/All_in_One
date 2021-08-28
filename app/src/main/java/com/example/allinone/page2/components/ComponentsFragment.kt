package com.example.allinone.page2.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.allinone.R
import com.example.allinone.databinding.FragmentComponentsBinding

class ComponentsFragment : Fragment() {
    private lateinit var binding: FragmentComponentsBinding
    private val viewModel: ComponentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentComponentsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()
        return binding.root
//        return inflater.inflate(R.layout.fragment_components, container, false)
    }

    private fun init() {
        binding.slider.addOnChangeListener { _, value, _ ->
            viewModel.setSliderNum(value)
            binding.textView6.text = String.format("%.2f", value)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ComponentsFragment()
    }

}