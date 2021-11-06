package com.example.allinone.page2

import androidx.fragment.app.FragmentContainerView
import androidx.navigation.*
import com.example.allinone.MainNavDirections
import com.example.allinone.R

object NavigationState {

    const val PAGE_COMPONENTS = 1
    const val PAGE_NOTIFICATION = 4
    const val PAGE_SERVICE = 5
    const val PAGE_BROADCAST = 6
    const val PAGE_VIBRATION = 7
    const val PAGE_CONTENT_PROVIDER = 8
    const val PAGE_INTERNET = 9
    const val PAGE_ROOM = 10
    const val PAGE_SUB_ROOM = 101

    fun levelOneCheck(navDestination: NavDestination) {
//        MainNavDirections.apply {
//            return when (navDestination) {
//                action
//            }
//        }

        if (navDestination.id == R.id.stopScheduleFragment) {

        }

    }
}