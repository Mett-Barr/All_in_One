package com.example.allinone.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsets.Type.ime
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.R
import com.example.allinone.databinding.ActivityMainBinding
import java.lang.reflect.Type
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
//        viewModel.context = this
        init()


        window.setDecorFitsSystemWindows(false)
    }

    private fun init() {

        viewPager()

        viewModel.pagerState.observe(this, Observer {
            when (it) {
                0 -> binding.VP.currentItem = 0
                1 -> binding.VP.currentItem = 1
            }
//            Toast.makeText(applicationContext, "$it", Toast.LENGTH_SHORT).show()
        })
    }

    private fun viewPager() {
        binding.VP.also {
            it.adapter = MainPagerAdapter.newInstance(this)
            it.offscreenPageLimit = 3
            it.setPageTransformer(DepthPageTransformer())
            it.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            binding.VP.isUserInputEnabled = false
                            window.insetsController?.hide(WindowInsets.Type.ime())
                            binding.VP.currentItem = 0
                        }
                        1 -> {
                            binding.VP.isUserInputEnabled = true
                            binding.VP.currentItem = 1
                        }
                    }
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    window.insetsController?.hide(android.view.WindowInsets.Type.ime())
                }
            })
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


    override fun onBackPressed() {
        when (binding.VP.currentItem) {
            0 -> this.finish()
            1 -> viewModel.goToPage1()
        }
    }

//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        Toast.makeText(this, "change", Toast.LENGTH_SHORT).show()
//        hide()
//    }
//
//    private fun hide() {
//        if (window.currentFocus?.equals(EditText(this)) == true) {
//            window.insetsController?.hide(WindowInsets.Type.ime())
//        }
//    }
}