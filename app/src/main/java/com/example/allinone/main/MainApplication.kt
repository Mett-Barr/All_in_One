package com.example.allinone.main

import android.app.Application
import com.example.allinone.page2.room.database.AppDatabase

class MainApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}