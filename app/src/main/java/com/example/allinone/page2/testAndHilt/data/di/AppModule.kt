package com.example.allinone.page2.testAndHilt.data.di

import android.content.Context
import androidx.room.Room
import com.example.allinone.page2.testAndHilt.data.DefaultRepository
import com.example.allinone.page2.testAndHilt.data.Repository
import com.example.allinone.page2.testAndHilt.data.local.TestItemDao
import com.example.allinone.page2.testAndHilt.data.local.TestItemDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

//    @Qualifier
//    @Retention(AnnotationRetention.RUNTIME)
//    annotation class AnnotationTest


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TestItemDataBase {
        return Room.databaseBuilder(
            appContext,
            TestItemDataBase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
//    @AnnotationTest
    @Singleton
    fun provideUserDao(database: TestItemDataBase): TestItemDao {
        return database.testItemDao()
    }

//    @Provides
//    @Singleton
//    fun provideRepository(testItemDao: TestItemDao) = DefaultRepository(testItemDao) as Repository
}

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(testItemDao: TestItemDao): Repository {
        return DefaultRepository(testItemDao)
    }
//    fun provideRepository(@AppModule.AnnotationTest testItemDao: TestItemDao): Repository {
//        return DefaultRepository(testItemDao)
//    }
}