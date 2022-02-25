package com.example.allinone.page2.testAndHilt.data.di

import android.content.Context
import androidx.room.Room
import com.example.allinone.page2.testAndHilt.data.local.TestItemDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    @Provides
    @Named("test_db")
    fun provideInMemoryDB(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, TestItemDataBase::class.java)
            .allowMainThreadQueries()
            .build()
}