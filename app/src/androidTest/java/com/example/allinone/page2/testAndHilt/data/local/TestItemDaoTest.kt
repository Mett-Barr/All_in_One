package com.example.allinone.page2.testAndHilt.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.allinone.main.MainApplication
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import org.junit.Assert.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TestItemDaoTest {

    val item = TestItem(0, 0)

    //    java.lang.IllegalStateException: Hilt test, com.example.allinone.page2.testAndHilt.data.local.TestItemDaoTest, cannot use a @HiltAndroidApp application but found com.example.allinone.main.MainApplication. To fix, configure the test to use HiltTestApplication or a custom Hilt test application generated with @CustomTestApplication.
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: TestItemDataBase

    //    @Inject
//    @Named("test_dao")
    lateinit var dao: TestItemDao

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
        val actual = 0
        val size = dao.getSize()
        assertEquals(actual, size)
    }

    @Test
    fun insertTest() = runBlockingTest {
        one()
        val actual = 1

        assertEquals(actual, dao.getSize())
        assertEquals(item, dao.getValue().first())
    }

    @Test
    fun deleteTest() = runBlockingTest {
        one()
        val actual = 0
        dao.delete(item)

        assertEquals(actual, dao.getSize())
        assertEquals(null, dao.getValue().first())
    }

    @Test
    fun getValueTest() = runBlockingTest {
        one()
        val actual = item.value

        assertEquals(actual, dao.getValue().first().value)
    }

    @Test
    fun valueChangeTest() = runBlockingTest {
        one()
        val actual = TestItem(0, 2)
        dao.valueChange(actual)

        assertEquals(actual, dao.getValue().first())
    }

//    @Test
//    fun test() {
//        assertEquals(1, 1)
//    }

    private fun one() = runBlocking {
        if (dao.getSize() == 0) {
            dao.insert(item)
        }
    }
}