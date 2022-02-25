package com.example.allinone.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.allinone.R
import com.example.allinone.databinding.ActivityMainBinding
import com.example.allinone.main.MainViewModel.Companion.level
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        init()


        window.setDecorFitsSystemWindows(false)
    }

    private fun init() {
        viewPager()
        state()
    }

    private fun viewPager() {
        binding.VP.also {
            it.adapter = MainPagerAdapter.newInstance(this)
            it.offscreenPageLimit = 1
            it.setPageTransformer(DepthPageTransformer())
            it.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            binding.VP.isUserInputEnabled = false
                            window.insetsController?.hide(WindowInsets.Type.ime())
                            viewModel.apply {
                                goToPage1()
//                                _goToState.value = 0
                            }
//                            binding.VP.currentItem = 0
                        }
                        1 -> {
                            binding.VP.isUserInputEnabled = true
//                            binding.VP.currentItem = 1
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

        viewModel.pagerState.observe(this, Observer {
            when (it) {
                0 -> binding.VP.currentItem = 0
                1 -> binding.VP.currentItem = 1
            }
            Log.d("!!!", "viewPager: " + binding.VP.currentItem.toString())
        })

        viewModel.goToState.observe(this, Observer {
            binding.VP.isUserInputEnabled = level(it) == 1
        })
    }

    private fun state() {
        viewModel.goToState.observe(this, Observer {
            ApplicationToast.show(this, viewModel.goToState.value.toString())
        })
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        when (binding.VP.currentItem) {
            0 -> this.finish()
            1 -> {
                viewModel.backPress()

//                viewModel.goToPage1()
//                if (viewModel.goToState.value == MainViewModel.PAGE_STOP_SCHEDULE) {
//                } else {
//                    Log.d("!!!", "onBackPressed: ")
//                }
            }
        }
    }


    override fun onPause() {
        super.onPause()
        ApplicationToast.cancel()
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

