package com.example.allinone.page2.testAndHilt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TestItem::class], version = 1, exportSchema = false)
abstract class TestItemDataBase : RoomDatabase() {

    abstract fun testItemDao(): TestItemDao

//    companion object {
//        @Volatile
//        private var INSTANCE: TestItemDataBase? = null
//        fun getDataBase(context: Context): TestItemDataBase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TestItemDataBase::class.java,
//                    "test_item_database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}