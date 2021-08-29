package com.example.allinone.page2.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.allinone.R
import com.example.allinone.databinding.FragmentNotificationBinding
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService


class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private val viewModel: NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()
        return binding.root
    }

    private fun init() {
        send()
    }

    private fun send() {

        val channelId = getString(R.string.channel_id)

        // 確認是否為Android 8.0以上版本
        // 8.0以上版本才需要建立通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            //設定通知渠道名稱、描述和重要性
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(channelId, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager =
                activity?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
        val builder = NotificationCompat.Builder(this.requireContext(), channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("Content")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Big Text")
            )

        binding.button7.setOnClickListener {
            builder.apply {
                if (binding.txTitle.text.toString() != "") {
                    setContentTitle(binding.txTitle.text)
                }
                if (binding.txContent.text.toString() != "") {
                    setContentText(binding.txContent.text)
                }
                if (binding.txBigText.text.toString() != "") {
                    setStyle(NotificationCompat.BigTextStyle()
                        .bigText(binding.txBigText.text))
                }
            }

            with(NotificationManagerCompat.from(this.requireContext())) {
                // notificationId is a unique int for each notification that you must define
                val notificationId = 0
                notify(notificationId, builder.build())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationFragment()
    }
}