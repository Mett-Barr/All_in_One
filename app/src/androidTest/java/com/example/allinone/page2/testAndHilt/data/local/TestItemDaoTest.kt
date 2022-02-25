package com.example.allinone.page2.testAndHilt.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import org.junit.Assert.*
import org.junit.runner.RunWith

@SmallTest
@HiltAndroidTest
//@RunWith(AndroidJUnit4::class)
class TestItemDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: TestItemDataBase
    private lateinit var dao: TestItemDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.testItemDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSizeTest() = runBlockingTest {
        val expected = 0
        val size = dao.getSize()
        assertEquals(expected, size)
    }

    @Test
    fun plusTest() = runBlockingTest {
//        val ex
        assertEquals(1, 1)
    }

    @Test
    fun test() {
        assertEquals(1, 1)
    }
}