package com.example.allinone.page2.testAndHilt.data.di

import com.example.allinone.page2.testAndHilt.data.FakeTestRepository
import com.example.allinone.page2.testAndHilt.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class FakeRepositoryModule {
    @Singleton
    @Binds
    abstract fun  bindRepository(repo: FakeTestRepository): Repository
}