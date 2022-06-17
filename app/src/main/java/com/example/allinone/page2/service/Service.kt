package com.example.allinone.page2.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.allinone.R
import java.util.*
import kotlin.concurrent.timerTask

class Service : Service() {

//    companion object {
//    }

    private val timer = Timer()
    private val notificationId = 1

    private lateinit var notificationManager: NotificationManager

    // 綁定時調用
    // 必須要實現的方法
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    // 使次創建時，系統將調用此方法來執行一次性程序（在調用onStartCommand() 或onBind() 之前）
    // 如果服務已經在運行，則不會掉用此方法。該方法只會被調用一次
    override fun onCreate() {
        super.onCreate()
        Log.d(ContentValues.TAG, "onCreate: ")
    }


    private fun noti() {
        val channelId = getString(R.string.service_channel_id)

        // 確認是否為Android 8.0以上版本
        // 8.0以上版本才需要建立通知渠道

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            //設定通知渠道名稱、描述和重要性
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(channelId, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager =
                this.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        var i = 0
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Service")
            .setContentText(i.toString())
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOnlyAlertOnce(true)
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText("Big Text")
//            )

        timer.schedule(timerTask {
            i += 1
            with(NotificationManagerCompat.from(this@Service)) {
                // notificationId is a unique int for each notification that you must define
                builder.setContentText(i.toString())
                notify(notificationId, builder.build())
            }

            if (i >= 5) {
                stopTimer()
            }
        }, 0, 1000)


//        with(NotificationManagerCompat.from(this)) {
//            // notificationId is a unique int for each notification that you must define
//            val notificationId = 0
//            notify(notificationId, builder.build())
//        }
    }

    private fun stopTimer() {
        timer.cancel()
        notificationManager =
            this.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId)
    }

    // 無法做到取消通知和停止計時
//    fun cancelNoti(context: Context) {
//        stopTimer()
//        with(NotificationManagerCompat.from(context)) {
//            cancel(notificationId)
//        }
//    }

    // 每次通過startService()方法啟動Service都會被回調
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(ContentValues.TAG, "onStartCommand: ")
        noti()
        return super.onStartCommand(intent, flags, startId)
    }

    // 服務銷毀時的回調
    override fun onDestroy() {
        super.onDestroy()
        Log.d(ContentValues.TAG, "onDestroy: ")

        stopTimer()
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d(ContentValues.TAG, "stopService: ")
        return super.stopService(name)
    }
}