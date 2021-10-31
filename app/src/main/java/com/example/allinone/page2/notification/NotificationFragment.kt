package com.example.allinone.page2.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import com.example.allinone.R
import com.example.allinone.databinding.FragmentNotificationBinding


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
        blank()
    }


    private fun send() {

        val channelId = getString(R.string.noti_channel_id)

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
                activity?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
        val builder = NotificationCompat.Builder(this.requireContext(), channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("Content")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Big\nText")
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
                    setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(binding.txBigText.text)
                    )
                }
            }

            with(NotificationManagerCompat.from(this.requireContext())) {
                // notificationId is a unique int for each notification that you must define
                val notificationId = 0
                notify(notificationId, builder.build())
            }
        }
    }

    private fun blank() {
        binding.notiLayout.setOnClickListener {
//            Toast.makeText(context, "!!!", Toast.LENGTH_SHORT).show()
//            it.requestFocus()
//            it.apply {
//                windowInsetsController?.hide(android.view.WindowInsets.Type.ime())
//                isFocusableInTouchMode = true
//                requestFocus()
//                isFocusableInTouchMode = false
//            }
//            hideKeyboard(it)
        }

        binding.notiLayout.setOnFocusChangeListener { view, b ->
//            view.windowInsetsController?.hide(android.view.WindowInsets.Type.ime())
            Log.d(TAG, "blank: ")
            hide()
        }
    }

    private fun hideKeyboard(it: View) {
        if (!it.isFocusableInTouchMode) {
            it.apply {
                windowInsetsController?.hide(android.view.WindowInsets.Type.ime())
                isFocusableInTouchMode = true
                requestFocus()
                isFocusableInTouchMode = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationFragment()
    }

    private fun hide() {
        activity?.window?.insetsController?.hide(WindowInsets.Type.ime())
    }

}