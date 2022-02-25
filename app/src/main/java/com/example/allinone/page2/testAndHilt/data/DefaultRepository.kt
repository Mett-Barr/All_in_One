package com.example.allinone.page2.testAndHilt.data

import com.example.allinone.page2.testAndHilt.data.local.TestItem
import com.example.allinone.page2.testAndHilt.data.local.TestItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val testItemDao: TestItemDao
) : Repository {
    override fun getValue(): Flow<TestItem> = testItemDao.getValue()

    override suspend fun getSize(): Int = testItemDao.getSize()

    override suspend fun insert(testItem: TestItem) = testItemDao.insert(testItem)

    override suspend fun valueChange(testItem: TestItem) = testItemDao.valueChange(testItem)
}