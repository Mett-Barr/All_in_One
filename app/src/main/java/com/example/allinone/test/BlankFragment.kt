package com.example.allinone.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.databinding.FragmentBlankBinding
import com.example.allinone.main.MainViewModel

// TODO: Rename parameter arguments, choose names that match
class BlankFragment : Fragment() {
    lateinit var BF2: BlankFragment2
    lateinit var viewPager2: ViewPager2

    //目前失敗，原本想試試函式物件的方法的
    lateinit var goToB3: () -> Unit
    lateinit var goToB4: () -> Unit

    lateinit var binding: FragmentBlankBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    private fun init() {
        binding.viewModel = viewModel

        var i = 0
        binding.button.setOnClickListener {
            when (viewModel.goToState.value) {
//                0 -> {
////                    BF2.goToB3()
////                    goToB3
//                    viewModel.goToB4()
////                    viewModel.state.value = 0
//                    i = 1
//                }

                1 -> {
//                    BF2.goToB4()
//                    goToB4
                    viewModel.goToComponents()
//                    viewModel.state.value = 1
                    i = 0
                }
            }
            viewPager2.setCurrentItem(1, true)
        }

        binding.button2.setOnClickListener {
            when (i) {
                0 -> {
//                    BF2.goToB3()
                    goToB3.invoke()
//                    viewModel.goToB3()
                    i = 1
                }

                1 -> {
//                    BF2.goToB4()
                    goToB4.invoke()
//                    viewModel.goToB4()
                    i = 0
                }
            }
            viewPager2.setCurrentItem(1, true)
        }


        viewModel.goToState.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            Log.d("TAG", "BF1: $it")
        })

        binding.button4.setOnClickListener {
            when(viewModel.goToState.value) {
//                0 -> viewModel.goToB4()
                1 -> viewModel.goToComponents()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BlankFragment().apply {
            }
    }

}