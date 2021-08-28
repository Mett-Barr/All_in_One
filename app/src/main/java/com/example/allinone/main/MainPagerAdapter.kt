package com.example.allinone.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.page2.Page2Fragment
import com.example.allinone.test.BlankFragment
import com.example.allinone.test.BlankFragment2

class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
//            BF1 = BlankFragment.newInstance()
//            BF1.viewPager2 = viewPager2
//            BF1
            MainFragment.newInstance()
        } else {
//            BF2 = BlankFragment2.newInstance()
//            BF1.BF2 = BF2
//            BF2.BF1 = BF1
//            BF1.BF2 = BF2
//            BF1.goToB3 = { BF2.goToB3() }
//            BF1.goToB4 = { BF2.goToB4() }
//            BF2
            Page2Fragment.newInstance()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    companion object {
        fun newInstance(fragmentActivity: FragmentActivity) = MainPagerAdapter(fragmentActivity)
    }
}
