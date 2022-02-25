package com.example.allinone.page2.testAndHilt.data

import com.example.allinone.page2.testAndHilt.data.local.TestItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeTestRepository : Repository {

    private val item = TestItem(0, 0)

    private val _fakeTestItem: MutableStateFlow<TestItem> = MutableStateFlow(item)
    val fakeTestItem: StateFlow<TestItem>
        get() = _fakeTestItem

    override fun getValue(): Flow<TestItem> = fakeTestItem

    override suspend fun getSize(): Int = fakeTestItem.value.value


    override suspend fun insert(testItem: TestItem) {
        _fakeTestItem.value = testItem
    }

    override suspend fun valueChange(testItem: TestItem) {
        _fakeTestItem.value = testItem
    }
}