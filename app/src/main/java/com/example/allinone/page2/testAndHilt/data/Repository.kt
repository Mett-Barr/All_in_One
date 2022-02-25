package com.example.allinone.page2.testAndHilt.data

import com.example.allinone.page2.testAndHilt.data.local.TestItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getValue(): Flow<TestItem>

    suspend fun getSize(): Int

    suspend fun insert(testItem: TestItem)

    suspend fun valueChange(testItem: TestItem)
}