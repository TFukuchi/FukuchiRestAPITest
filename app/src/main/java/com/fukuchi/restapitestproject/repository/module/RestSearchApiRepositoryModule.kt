package com.fukuchi.restapitestproject.repository.module

import com.fukuchi.restapitestproject.repository.RestSearchApiRepository
import com.fukuchi.restapitestproject.repository.impl.RestSearchApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * RepositoryのHilt注入
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RestSearchApiRepositoryModule {
    @Binds
    abstract fun bindRestSearchApiRepositoryImpl(impl: RestSearchApiRepositoryImpl): RestSearchApiRepository
}