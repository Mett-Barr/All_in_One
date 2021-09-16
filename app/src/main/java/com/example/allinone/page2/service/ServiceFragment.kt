package com.example.allinone.page2.service

import android.content.ComponentName
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.allinone.databinding.FragmentServiceBinding

class ServiceFragment : Fragment() {
    private lateinit var binding: FragmentServiceBinding
//    private val viewModel: MainViewModel by activityViewModels()

    /** ----------------------Binder--------------------------------  */
    private lateinit var binderService: BinderService
    private var binderBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as BinderService.LocalBinder
            binderService = binder.getService()
            binderBound = true

            Log.d("TAG", "onServiceConnected: ")
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            binderBound = false
        }
    }

    /**---------------------------Binder----------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
        init()
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // binder onStart
        Intent(activity, BinderService::class.java).also {
            activity?.bindService(it, binderConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()

        // binder onStop
        activity?.unbindService(binderConnection)
        binderBound = false
    }

    private fun init() {
        serviceInit()
        binderInit()
        messengerInit()
    }

    private fun serviceInit() {
        var boo = true
        val intent = Intent(activity, Service::class.java)
        binding.serviceButton.setOnClickListener {
            boo = if (boo) {
                activity?.startService(intent)
                binding.serviceButton.text = "stop"
                !boo
            } else {
                activity?.stopService(intent)
                binding.serviceButton.text = "start"
                Service().stopTimer()
//                context?.let { it1 -> Service().cancelNoti(it1) }
                !boo
            }
        }
    }

    private fun binderInit() {
        binding.binderButton.setOnClickListener {
            if (binderBound) {
                // Call a method from the LocalService.
                // However, if this call were something that might hang, then this request should
                // occur in a separate thread to avoid slowing down the activity performance.
                val num: Int = binderService.randomNumber
                Toast.makeText(activity, "number: $num", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "false", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun messengerInit() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = ServiceFragment()
    }
}