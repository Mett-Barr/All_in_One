package com.example.allinone.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.allinone.page2.Page2Fragment

class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment =
        if (position == 0) MainFragment.newInstance() else Page2Fragment.newInstance()

    override fun getItemCount(): Int = 2

    companion object {
        fun newInstance(fragmentActivity: FragmentActivity) = MainPagerAdapter(fragmentActivity)
    }
}
