package com.example.allinone.page2.broadcast.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryBroadcast : BroadcastReceiver() {
    var text = "50%"
    var value = 50

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null) {
            value = p1.extras?.getInt("level")!!
            text = "$value%"
            Toast.makeText(p0, "$text", Toast.LENGTH_SHORT).show()
        }
    }
}