package com.example.allinone.page2.service

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class BinderService : Service() {
    // Binder given to clients
    private val binder = LocalBinder()

    // Random number generator
    private val mGenerator = Random()

    /** method for clients  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): BinderService = this@BinderService
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        return binder
    }
}