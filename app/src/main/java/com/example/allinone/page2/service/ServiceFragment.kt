package com.example.allinone.page2.service

import android.content.ComponentName
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.allinone.databinding.FragmentServiceBinding
import com.example.allinone.main.MainViewModel

class ServiceFragment : Fragment() {
    private lateinit var binding: FragmentServiceBinding
    private val viewModel: MainViewModel by activityViewModels()

    /** ----------------------Binder--------------------------------  */
    private lateinit var binderService: BinderService
    private var binderBound: Boolean = false
    lateinit var binderToast: Toast

    /** Defines callbacks for service binding, passed to bindService()  */
    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as BinderService.LocalBinder
            binderService = binder.getService()
            binderBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            binderBound = false
        }
    }
    /**---------------------------Binder----------------------------*/


    /**--------------------------Messenger--------------------------*/
    /** Messenger for communicating with the service.  */
    private var messengerService: Messenger? = null

    /** Flag indicating whether we have called bind on the service.  */
    private var messengerBound: Boolean = false

    /**
     * Class for interacting with the main interface of the service.
     */
    private val messengerConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            /**
             * 4.客戶端使用IBinder(service)將Messenger（引用服務的Handler）實例化，
             * 然後使用Messenger將Message 對象發送給服務(messengerService)
             */
            messengerService = Messenger(service)
            messengerBound = true
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            messengerService = null
            messengerBound = false
        }
    }

    /** 用於接收服務返回的消息 */
    private var mReceiverReplyMsg: Messenger = Messenger(ReceiverReplyMsgHandler())

    // internal到底怎麼用........
    inner class ReceiverReplyMsgHandler
        (
//        context: Context,
//        private val applicationContext: Context = context
    )
        :
        Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO ->
//                    newInstance().contextTest()
                    Toast.makeText(context, "Fragment got msg from MessengerService!", Toast.LENGTH_SHORT)
                        .show()
//                    Log.d("TAG", "handleMessage: ")

                else -> super.handleMessage(msg)
            }
        }
    }

    private fun sayHello(v: View) {
        if (!messengerBound) return
        // 創建與服務交互的消息實體Message
        // Create and send a message to the service, using a supported 'what' value
        val msg: Message = Message.obtain(null, MSG_SAY_HELLO, 0, 0)
        // 把接受服務端回復的Messenger通過Message的replyTo參數傳遞給服務端
        msg.replyTo = mReceiverReplyMsg
        try {
            messengerService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }


    /**--------------------------Messenger--------------------------*/


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

        // Messenger onStart
        Intent(activity, MessengerService::class.java).also { intent ->
            activity?.bindService(intent, messengerConnection, Context.BIND_AUTO_CREATE)
        }

        if (context == null) {
            Log.d(TAG, "onStart: ")
        }
    }

    override fun onStop() {
        super.onStop()

        // binder onStop
        activity?.unbindService(binderConnection)
        binderBound = false

        // messenger onStop
        if (messengerBound) {
            activity?.unbindService(messengerConnection)
            messengerBound = false
        }

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
                if (this::binderToast.isInitialized) {
                    binderToast.cancel()
                }
                binderToast = Toast.makeText(activity, "number: $num", Toast.LENGTH_SHORT)
                binderToast.show()
            }
        }
    }

    private fun messengerInit() {
        binding.messengerButton.setOnClickListener {
            sayHello(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ServiceFragment()
    }
}