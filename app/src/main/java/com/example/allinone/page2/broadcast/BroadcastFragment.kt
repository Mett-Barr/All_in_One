package com.example.allinone.page2.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.allinone.databinding.FragmentBroadcastBinding
import com.example.allinone.page2.broadcast.battery.BatteryBroadcast

class BroadcastFragment : Fragment() {
    private lateinit var binding: FragmentBroadcastBinding

    val br = BatteryBroadcast()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBroadcastBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    private fun init() {
        orderedBroadcast()
        broadcast()
        localBroadcastManager()
        batteryBroadcast()
    }

    private fun orderedBroadcast() {
    }

    private fun broadcast() {
    }

    private fun localBroadcastManager() {
    }

    private fun batteryBroadcast() {
        var boo = false
        binding.batteryState.text = br.text
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        binding.testButton.setOnClickListener {
            if (boo) {
                context?.unregisterReceiver(br)
                binding.testButton.text = "register"
                boo = false
            } else {
                context?.registerReceiver(br, filter)
                binding.testButton.text = "unregister"
                boo = true
            }
        }

        binding.re.setOnClickListener {
            binding.batteryState.text = br.text
            Toast.makeText(context, br.text, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BroadcastFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(br)
    }
}