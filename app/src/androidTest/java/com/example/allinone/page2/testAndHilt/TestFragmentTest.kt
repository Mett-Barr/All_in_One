package com.example.allinone.page2.testAndHilt

import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.allinone.page2.testAndHilt.data.Repository
import com.example.allinone.page2.testAndHilt.data.local.TestItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class TestFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

//    val activityScenario = launch(MainActivity::class.java)


    @Inject
    lateinit var repository: Repository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    suspend fun test() {

//        val fragmentScenario = launchFragment<TestFragment>()

        repository.insert(TestItem(0, 0))

        assertEquals(1, 1)
    }
}