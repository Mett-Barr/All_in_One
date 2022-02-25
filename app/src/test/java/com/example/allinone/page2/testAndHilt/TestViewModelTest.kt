package com.example.allinone.page2.testAndHilt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.allinone.MainCoroutineRule
import com.example.allinone.page2.testAndHilt.data.FakeTestRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
//import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestViewModelTest {

    private lateinit var viewModel: TestViewModel

    private lateinit var repository: FakeTestRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // DI 未完成
    @Before
    fun setUp() {
        repository = FakeTestRepository()
        viewModel = TestViewModel(repository)
    }

//    @Test
//    fun getTest() {
//
//    }

//    @Test
//    fun getTestRoomItem() {
//    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRoomEmptyCheck() = runBlockingTest {
        viewModel.testRoomEmptyCheck()
        val expected = repository.getValue()
        assertNotNull(expected)
    }

    @Test
    fun plus() {
        val num = viewModel.testRoomItem.value?.value
        viewModel.plus()
        assertEquals(num?.plus(1), viewModel.testRoomItem.value?.value)
    }

    @Test
    fun minus() {
        val num = viewModel.testRoomItem.value?.value
        viewModel.minus()
        assertEquals(num?.minus(1), viewModel.testRoomItem.value?.value)
    }
}