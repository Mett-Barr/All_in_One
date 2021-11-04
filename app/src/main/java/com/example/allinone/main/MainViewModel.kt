package com.example.allinone.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    /**--------------Navigation------------------*/
    val _goToState = MutableLiveData(0)
    val goToState: LiveData<Int>
        get() = _goToState

     val _backControl = MutableLiveData(true)
    val backControl: LiveData<Boolean> = _backControl

    fun goToComponents() {
        _goToState.value = PAGE_COMPONENTS
        goToPage2()
    }

    fun goToNotification() {
        _goToState.value = PAGE_NOTIFICATION
        goToPage2()
    }

    fun goToService() {
        _goToState.value = PAGE_SERVICE
        goToPage2()
    }

    fun goToBroadcast() {
        _goToState.value = PAGE_BROADCAST
        goToPage2()
    }

    fun goToVibration() {
        _goToState.value = PAGE_VIBRATION
        goToPage2()
    }
    /**--------------Navigation------------------*/

    /**---------------Pager-----------------------*/
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
    /**---------------Pager-----------------------*/



    companion object {
        const val PAGE_COMPONENTS = 1
        const val PAGE_NOTIFICATION = 4
        const val PAGE_SERVICE = 5
        const val PAGE_BROADCAST = 6
        const val PAGE_VIBRATION = 7
        const val PAGE_CONTENT_PROVIDER = 8
        const val PAGE_INTERNET = 9
        const val PAGE_ROOM = 10
        const val PAGE_SUB_ROOM = 11
    }
}