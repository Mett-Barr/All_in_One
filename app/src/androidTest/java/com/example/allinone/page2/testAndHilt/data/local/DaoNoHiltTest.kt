package com.example.allinone.page2.testAndHilt.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoNoHiltTest {

    val testItem = TestItem(0, 0)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TestItemDataBase
    private lateinit var dao: TestItemDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestItemDataBase::class.java
        ).allowMainThreadQueries().build()

        dao = database.testItemDao()
    }

    @After
    fun teardown() = database.close()

    @Test
    fun getSizeTest() = runBlockingTest {
        val actual = 0
        assertThat(actual, `is`(dao.getSize()))
        one()
    }

    // `insert and delete test`
    @Test
    fun insertAndDeleteTest() = runBlockingTest {
        one()
        assertThat(dao.getValue().first(), `is`(testItem))
    }

    @Test
    fun getValueTest() = runBlockingTest {
        one()
        val actual = 0
        val excepted = dao.getValue().first().value
        assertThat(excepted, `is`(actual))
    }

    @Test
    fun valueChangeTest() = runBlockingTest {
        one()
        val testItem2 = TestItem(0, 2)
        dao.valueChange(testItem2)
        assertThat(dao.getValue().first().value, `is`(testItem2.value))
    }

    private fun one() = runBlocking {
        if (dao.getSize() == 0) dao.insert(testItem)
    }
}