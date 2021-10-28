package com.example.allinone.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//val PAGE_COMPONENTS = 1
//val PAGE_NOTIFICATION = 4
//val PAGE_SERVICE = 5
//val PAGE_BROADCAST = 6
//val PAGE_VIBRATION = 7
//val PAGE_CONTENT_PROVIDER = 8

//const val PAGE_COMPONENTS = 1
//const val PAGE_NOTIFICATION = 4
//const val PAGE_SERVICE = 5
//const val PAGE_BROADCAST = 6
//const val PAGE_VIBRATION = 7
//const val PAGE_CONTENT_PROVIDER = 8

class MainViewModel : ViewModel() {
//    val PAGE_COMPONENTS = 1
//    val PAGE_NOTIFICATION = 4
//    val PAGE_SERVICE = 5
//    val PAGE_BROADCAST = 6
//    val PAGE_VIBRATION = 7
//    val PAGE_CONTENT_PROVIDER = 8

//    lateinit var context: Context

    /**--------------Navigation------------------*/
    val _goToState = MutableLiveData(0)
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
    }
}