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
    val br2 = MyBroadcastReceiver()

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
        batteryBroadcast()
        testBroadcast()
    }

    private fun batteryBroadcast() {
        var boo = false
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
            binding.re.isEnabled = boo
        }

        binding.re.setOnClickListener {
            binding.batteryState.text = br.text
            Toast.makeText(context, br.text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun testBroadcast() {
        IntentFilter().also {
            it.addAction("do it")
            context?.registerReceiver(br2, it)
        }
        binding.re2.setOnClickListener {
            Intent().also { intent ->
                intent.action = "do it"
                context?.sendBroadcast(intent)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BroadcastFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.apply {
            unregisterReceiver(br)
            unregisterReceiver(br2)
        }
    }
}