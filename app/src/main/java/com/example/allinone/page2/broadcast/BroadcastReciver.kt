package com.example.allinone.page2.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null && p1.action.equals("do it")) {
            Toast.makeText(p0, "got it!", Toast.LENGTH_SHORT).show()
        }
    }
}