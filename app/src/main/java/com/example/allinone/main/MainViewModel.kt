package com.example.allinone.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allinone.R

class MainViewModel : ViewModel() {
    /**--------------Navigation------------------*/
    val _goToState = MutableLiveData(0)
    val goToState: LiveData<Int>
        get() = _goToState

    val _backControl = MutableLiveData(true)
    val backControl: LiveData<Boolean> = _backControl

    fun backPress() {
        _backControl.value = !_backControl.value!!
    }

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
        const val PAGE_FULL_SCHEDULE = 10
        const val PAGE_STOP_SCHEDULE = 101

        fun ridToState(int: Int?): Int {
            return when (int) {
                R.id.componentsFragment -> 1
                R.id.notificationFragment -> 4
                R.id.serviceFragment -> 5
                R.id.boardcastFragment -> 6
                R.id.vibrationFragment -> 7
                R.id.contentProviderFragment -> 8
                R.id.internetFragment -> 9
                R.id.fullScheduleFragment -> 10
                R.id.stopScheduleFragment -> 101
                else -> 0
            }
        }

        fun stateToRid(int: Int): Int {
            return when (int) {
                PageName.PAGE_COMPONENTS.i -> PageName.PAGE_COMPONENTS.rid
                PageName.PAGE_NOTIFICATION.i -> PageName.PAGE_NOTIFICATION.rid

                else -> 0
            }

        }

        fun levelCheck(state: Int): Int {
            return when (state) {
                0 -> 0
                in 1..100 -> 1
                else -> 2
            }
        }
    }
}

enum class PageName(val i: Int, val rid: Int) {
    PAGE_COMPONENTS(1, R.id.componentsFragment),
    PAGE_NOTIFICATION(4, R.id.notificationFragment)
}