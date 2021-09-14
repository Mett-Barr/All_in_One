package com.example.allinone.page2.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.allinone.R
import com.example.allinone.databinding.FragmentComponentsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

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
        slider()
        snackbarInit()
    }

    private fun slider() {
        binding.slider.addOnChangeListener { _, value, _ ->
            viewModel.setSliderNum(value)
            binding.textView6.text = String.format("%.2f", value)
        }
    }

    private fun snackbarInit() {
//        binding.button9.setOnClickListener {
//            val snackbar = Snackbar.make(binding.Container, "Basic", Snackbar.LENGTH_SHORT)
//            snackbar.setAction("Dismiss") {
//                snackbar.dismiss()
//            }
//            snackbar.show()
//        }

        binding.button8.setOnClickListener {
            val snackbar = Snackbar.make(binding.Container, "Work!", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Toast") {
                snackbar.dismiss()
                Toast.makeText(activity, "Toast!", Toast.LENGTH_SHORT).show()
            }

//            val layout = snackbar.view as SnackbarLayout
//            val textView: TextView =
//                layout.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
//            textView.visibility = View.INVISIBLE

            snackbar.show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ComponentsFragment()
    }

}