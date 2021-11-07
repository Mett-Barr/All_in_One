package com.example.allinone.main

import android.content.Context
import android.widget.Toast

object ApplicationToast {
    lateinit var toast: Toast

    fun showToast(context: Context, str: String) {
        if (this::toast.isInitialized) {
//            Thread.sleep(1000)
            toast.cancel()
        }
        toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cancel() {
        toast.cancel()
    }
}
