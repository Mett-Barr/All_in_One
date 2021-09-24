package com.example.allinone.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.R
import com.example.allinone.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.context = this
        init()
    }

    private fun init() {
        binding.VP.adapter = MainPagerAdapter.newInstance(this)
        binding.VP.offscreenPageLimit = 3
        binding.VP.setPageTransformer(DepthPageTransformer())
        binding.VP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.VP.isUserInputEnabled = false
                    1 -> binding.VP.isUserInputEnabled = true
                }
            }
        })

        viewModel.pagerState.observe(this, Observer {
            when(it) {
                0 -> binding.VP.currentItem = 0
                1 -> binding.VP.currentItem = 1
            }
        })
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


    override fun onBackPressed() {
        when(binding.VP.currentItem) {
            0 -> this.finish()
            1 -> viewModel.goToPage1()
        }
    }
}