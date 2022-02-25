package com.example.allinone.page2.testAndHilt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
//import org.junit.Assert.*

import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestViewModelTest {

    private lateinit var tasksViewModel: TestViewModel

//    private lateinit var tasksRepository: FakeTestRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // DI 未完成
//    @Before
//    fun setUp() {
//        tasksViewModel = TestViewModelFactory(ApplicationProvider.getApplicationContext())
//    }

    @After
    fun tearDown() {
    }

    @Test
    fun getTest() {
    }

    init {}

    @Test
    fun getTestRoomItem() {
    }

    @Test
    fun testRoomEmptyCheck() {
    }

    @Test
    fun plus() {
    }

    @Test
    fun minus() {
    }
}