package com.example.allinone.page2.vibration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allinone.R
import com.example.allinone.databinding.FragmentVibrationBinding

class VibrationFragment : Fragment() {
    private lateinit var binding: FragmentVibrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVibrationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        init()

        return binding.root
    }

    private fun init() {
        button()
        test()
    }

    private fun button() {
        binding.rv.apply {
            adapter = CustomAdapter(resources.getStringArray(R.array.haptic_feedback_constants))
            layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun test() {
        binding.button11.also {
    //            it.setOnClickListener { view ->
    //                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
    //            }
            it.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_PRESS)
                    MotionEvent.ACTION_UP -> v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_RELEASE)
                }
                false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = VibrationFragment()
    }
}