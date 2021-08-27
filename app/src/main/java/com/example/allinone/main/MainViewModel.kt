package com.example.allinone.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _goToState = MutableLiveData(0)
    val goToState: LiveData<Int>
        get() = _goToState

    var state = MutableLiveData(0)

    fun goToB3() {
        _goToState.value = 0
    }

    fun goToB4() {
        _goToState.value = 1
    }
}