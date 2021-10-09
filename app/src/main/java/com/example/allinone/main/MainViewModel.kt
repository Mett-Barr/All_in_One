package com.example.allinone.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//const val PAGE_COMPONENTS = 2
//const val PAGE_NOTIFICATION = 5
//const val PAGE_SERVICE = 6
//const val PAGE_BROADCAST = 7

class MainViewModel : ViewModel() {
    val PAGE_COMPONENTS = 2
    val PAGE_NOTIFICATION = 5
    val PAGE_SERVICE = 6
    val PAGE_BROADCAST = 7

//    lateinit var context: Context

    /**--------------Navigation------------------*/
    private val _goToState = MutableLiveData(0)
    val goToState: LiveData<Int>
        get() = _goToState

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
}