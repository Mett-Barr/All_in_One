package com.example.allinone.page2.testAndHilt

import androidx.lifecycle.*
import com.example.allinone.page2.testAndHilt.data.Repository
import com.example.allinone.page2.testAndHilt.data.local.TestItem
import com.example.allinone.page2.testAndHilt.data.local.TestItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _test = MutableLiveData(0)
    val test: LiveData<Int>
        get() = _test

    fun new() {
        _test.value = (0..10).random()
    }


    /**---------------------------------   Room    ----------------------------------------------**/

    val testRoomItem: LiveData<TestItem> = repository.getValue().asLiveData()


    private fun valueChange(int: Int = 0) {
        viewModelScope.launch { repository.valueChange(TestItem(0, int)) }
    }

    fun testRoomEmptyCheck() {
        viewModelScope.launch {
            if (repository.getSize() == 0) {
                repository.insert(TestItem(0, 0))
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

//class TestViewModelFactory(
//    private val testItemDao: TestItemDao,
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TestViewModel(testItemDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}