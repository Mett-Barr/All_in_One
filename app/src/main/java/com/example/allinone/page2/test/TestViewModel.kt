package com.example.allinone.page2.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {
    val _test = MutableLiveData(0)
    val test: LiveData<Int>
        get() = _test

    fun new() {
        _test.value = (0..10).random()
    }
}