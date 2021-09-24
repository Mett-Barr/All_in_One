package com.example.allinone.page2.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast

/** Command to the service to display a message  */
const val MSG_SAY_HELLO = 1

class MessengerService : Service() {

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private lateinit var mMessenger: Messenger

    /** 1.服務實現一個Handler，由其接收來自客戶端的每個調用的回調  */
    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        /** 5.服務在其Handler 中（在handleMessage() 方法中）接收每個Message */
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO -> {
                    Toast.makeText(applicationContext, "MessengerService got msg from Fragment!", Toast.LENGTH_SHORT).show()
                    // 回復客戶端訊息，該訊息由客戶端傳來
                    val client: Messenger = msg.replyTo
                    // 獲取回復訊息的消息實體
                    val replyMsg: Message = Message.obtain(null, MSG_SAY_HELLO)
                    Bundle().also {
                        it.putString("reply", "Reply success")
                        replyMsg.data = it
                    }

                    try {
                        client.send(replyMsg)
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    /** 3.Messenger 創建一個IBinder，服務通過onBind() 使其返回客戶端 */
    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
//        Toast.makeText(applicationContext, "Messenger bound", Toast.LENGTH_SHORT).show()
        /** 2.Handler 用於創建Messenger 對象（對Handler 的引用） */
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }
}