package com.example.allinone.page2.test

import androidx.lifecycle.*
import com.example.allinone.page2.test.testData.TestItem
import com.example.allinone.page2.test.testData.TestItemDao
import kotlinx.coroutines.launch

class TestViewModel(
    private val testItemDao: TestItemDao,
) : ViewModel() {
    private val _test = MutableLiveData(0)
    val test: LiveData<Int>
        get() = _test

    fun new() {
        _test.value = (0..10).random()
    }


    val testRoomItem: LiveData<TestItem> = testItemDao.getValue().asLiveData()

//    fun testRoomIsEmpty(): Boolean {
//        return testItemDao.getSize() == 0
//    }

    private fun valueChange(int: Int = 0) {
        viewModelScope.launch { testItemDao.valueChange(TestItem(0, int)) }
    }

    fun testRoomEmptyCheck() {
        viewModelScope.launch {
            if (testItemDao.getSize() == 0) {
                testItemDao.insert(TestItem(0, 0))
            }
        }
    }

    fun plus() {
        val num = testRoomItem.value?.value?.plus(1)
        num?.let { valueChange(it) }
    }

    fun minus() {
        val num = testRoomItem.value?.value?.minus(1)
        num?.let { valueChange(it) }
    }

}

class TestViewModelFactory(
    private val testItemDao: TestItemDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TestViewModel(testItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}