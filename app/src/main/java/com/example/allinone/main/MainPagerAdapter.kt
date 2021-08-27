package com.example.allinone.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.test.BlankFragment
import com.example.allinone.test.BlankFragment2

class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private lateinit var BF1: BlankFragment
    private lateinit var BF2: BlankFragment2
    lateinit var viewPager2: ViewPager2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            BF1 = BlankFragment.newInstance()
            BF1.viewPager2 = viewPager2
            BF1
        } else {
            BF2 = BlankFragment2.newInstance()
//            BF1.BF2 = BF2
            BF2.BF1 = BF1
            BF1.BF2 = BF2
            BF1.goToB3 = { BF2.goToB3() }
            BF1.goToB4 = { BF2.goToB4() }
            BF2
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    fun setPager(VP2: ViewPager2) {
        this.viewPager2 = VP2
    }

    companion object {
        fun newInstance(fragmentActivity: FragmentActivity, viewPager2: ViewPager2) =
            MainPagerAdapter(fragmentActivity).apply {
                this.viewPager2 = viewPager2
            }
    }
}
