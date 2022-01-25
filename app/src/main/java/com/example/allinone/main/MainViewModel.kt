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

//    fun goToComponents() {
//        _goToState.value = PAGE_COMPONENTS
//        goToPage2()
//    }
//
//    fun goToNotification() {
//        _goToState.value = PAGE_NOTIFICATION
//        goToPage2()
//    }
//
//    fun goToService() {
//        _goToState.value = PAGE_SERVICE
//        goToPage2()
//    }
//
//    fun goToBroadcast() {
//        _goToState.value = PAGE_BROADCAST
//        goToPage2()
//    }
//
//    fun goToVibration() {
//        _goToState.value = PAGE_VIBRATION
//        goToPage2()
//    }
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
//        const val PAGE_COMPONENTS = 1
//        const val PAGE_NOTIFICATION = 4
//        const val PAGE_SERVICE = 5
//        const val PAGE_BROADCAST = 6
//        const val PAGE_VIBRATION = 7
//        const val PAGE_CONTENT_PROVIDER = 8
//        const val PAGE_INTERNET = 9
//        const val PAGE_FULL_SCHEDULE = 10
//        const val PAGE_STOP_SCHEDULE = 101

        fun stateFromRid(rid: Int?): Int {
            return when (rid) {
                PageName.PAGE_COMPONENTS.rid -> PageName.PAGE_COMPONENTS.state
                PageName.PAGE_NOTIFICATION.rid -> PageName.PAGE_NOTIFICATION.state
                PageName.PAGE_SERVICE.rid -> PageName.PAGE_SERVICE.state
                PageName.PAGE_BROADCAST.rid -> PageName.PAGE_BROADCAST.state
                PageName.PAGE_VIBRATION.rid -> PageName.PAGE_VIBRATION.state
                PageName.PAGE_CONTENT_PROVIDER.rid -> PageName.PAGE_CONTENT_PROVIDER.state
                PageName.PAGE_INTERNET.rid -> PageName.PAGE_INTERNET.state
                PageName.PAGE_FULL_SCHEDULE.rid -> PageName.PAGE_FULL_SCHEDULE.state
                PageName.PAGE_WORK_MANAGER.rid -> PageName.PAGE_WORK_MANAGER.state
                PageName.PAGE_TEST.rid -> PageName.PAGE_TEST.state

                PageName.PAGE_STOP_SCHEDULE.rid -> PageName.PAGE_STOP_SCHEDULE.state

                else -> PageName.PAGE_TEST.state
            }
        }

        fun ridFromState(state: Int): Int {
            return when (state) {
                PageName.PAGE_COMPONENTS.state -> PageName.PAGE_COMPONENTS.rid
                PageName.PAGE_NOTIFICATION.state -> PageName.PAGE_NOTIFICATION.rid
                PageName.PAGE_SERVICE.state -> PageName.PAGE_SERVICE.rid
                PageName.PAGE_BROADCAST.state -> PageName.PAGE_BROADCAST.rid
                PageName.PAGE_VIBRATION.state -> PageName.PAGE_VIBRATION.rid
                PageName.PAGE_CONTENT_PROVIDER.state -> PageName.PAGE_CONTENT_PROVIDER.rid
                PageName.PAGE_INTERNET.state -> PageName.PAGE_INTERNET.rid
                PageName.PAGE_FULL_SCHEDULE.state -> PageName.PAGE_FULL_SCHEDULE.rid
                PageName.PAGE_WORK_MANAGER.state -> PageName.PAGE_WORK_MANAGER.rid
                PageName.PAGE_TEST.state -> PageName.PAGE_TEST.rid

                PageName.PAGE_STOP_SCHEDULE.state -> PageName.PAGE_STOP_SCHEDULE.rid

                else -> PageName.PAGE_TEST.rid
            }


        }

        fun level(state: Int): Int {
            return when (state) {
                0 -> 0
                in 1..100 -> 1
                else -> 2
            }
        }
    }

}

enum class PageName(val state: Int, val rid: Int) {
    PAGE_COMPONENTS(1, R.id.componentsFragment),
    PAGE_NOTIFICATION(4, R.id.notificationFragment),
    PAGE_SERVICE(5, R.id.serviceFragment),
    PAGE_BROADCAST(6, R.id.boardcastFragment),
    PAGE_VIBRATION(7, R.id.vibrationFragment),
    PAGE_CONTENT_PROVIDER(8, R.id.contentProviderFragment),
    PAGE_INTERNET(9, R.id.internetFragment),
    PAGE_FULL_SCHEDULE(10, R.id.fullScheduleFragment),
    PAGE_WORK_MANAGER(11, R.id.workManagerFragment),
    PAGE_TEST(12, R.id.testFragment),

    PAGE_STOP_SCHEDULE(101, R.id.stopScheduleFragment),
}
