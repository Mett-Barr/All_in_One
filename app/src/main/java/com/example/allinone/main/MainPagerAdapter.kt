package com.example.allinone.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.allinone.test.BlankFragment
import com.example.allinone.test.BlankFragment2

class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    lateinit var BF1: BlankFragment
    lateinit var BF2: BlankFragment2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            BF1 = BlankFragment.newInstance()
            BF1
        } else {
            BF2 = BlankFragment2.newInstance()
//            BF1.BF2 = BF2
            BF2.BF1 = BF1
            BF2
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
