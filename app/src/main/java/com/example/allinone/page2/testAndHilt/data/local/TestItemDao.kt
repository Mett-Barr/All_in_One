package com.example.allinone.page2.testAndHilt.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TestItemDao {
    @Query("SELECT * FROM Testitem WHERE id = 0")
    fun getValue(): Flow<TestItem>

    @Query("SELECT COUNT(*) from TestItem")
    suspend fun getSize(): Int

    @Insert
    suspend fun insert(testItem: TestItem)

    @Update
    suspend fun valueChange(testItem: TestItem)

    @Delete
    suspend fun delete(testItem: TestItem)
}