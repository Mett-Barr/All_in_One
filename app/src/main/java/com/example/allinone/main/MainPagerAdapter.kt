package com.example.allinone.main

import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.page2.Page2Fragment
import kotlin.math.abs

class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment =
        if (position == 0) MainFragment.newInstance() else Page2Fragment.newInstance()

    override fun getItemCount(): Int = 2

    companion object {
        fun newInstance(fragmentActivity: FragmentActivity) = MainPagerAdapter(fragmentActivity)
    }
}

/**---------------------------動畫---------------------------*/
@RequiresApi(21)
class DepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.alpha = 0f
        } else if (position <= 0) { // [-1,0]
            view.alpha = 1 + position
            view.translationX = pageWidth * -position
            view.translationZ = -1f
            val scaleFactor = (MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - abs(position)))
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.scrollBarFadeDuration

//                // Use the default slide transition when moving to the left page
//                view.setAlpha(1f);
//                view.setTranslationX(0f);
//                view.setTranslationZ(0f);
//                view.setScaleX(1f);
//                view.setScaleY(1f);
        } else if (position <= 1) { // (0,1]
            view.alpha = 1f
            view.translationX = 0f
            view.translationZ = 0f
            view.scaleX = 1f
            view.scaleY = 1f


//                // Fade the page out.
//                view.setAlpha(1 - position);
//
//                // Counteract the default slide transition
//                view.setTranslationX(pageWidth * -position);
//                // Move it behind the left page
//                view.setTranslationZ(-1f);
//
//                // Scale the page down (between MIN_SCALE and 1)
//                float scaleFactor = MIN_SCALE
//                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.alpha = 0f
        }
    }

    companion object {
        private const val MIN_SCALE = 0.75f
    }
}

