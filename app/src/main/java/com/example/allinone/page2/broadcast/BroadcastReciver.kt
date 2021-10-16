package com.example.allinone.page2.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {
    var text = "50%"
    var value = 50

    override fun onReceive(context: Context, intent: Intent) {
//        intent.getInt
    }
}