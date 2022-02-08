package com.example.allinone.page2.test.testData

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestItem(
    @PrimaryKey val id: Int = 0,
    @NonNull @ColumnInfo(name = "value") var value: Int
)