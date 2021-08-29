package com.example.allinone.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _goToState = MutableLiveData(0)
    val goToState: LiveData<Int>
        get() = _goToState

    fun goToB3() {
        _goToState.value = 0
        goToPage2()
    }

    fun goToB4() {
        _goToState.value = 1
        goToPage2()
    }

    fun goToNotification() {
        _goToState.value = 2
        goToPage2()
    }


    private val _pagerState = MutableLiveData(0)
    val pagerState: LiveData<Int>
        get() = _pagerState

    fun goToPage1() {
        _pagerState.value = 0
    }

    fun goToPage2() {
        _pagerState.value = 1
    }

    private val _pagerClickable = MutableLiveData(true)
    val pagerClickable: LiveData<Boolean>
        get() = _pagerClickable

    fun clickable() {
        _pagerClickable.value = true
    }

    fun nonClickable() {
        _pagerClickable.value = false
    }
}