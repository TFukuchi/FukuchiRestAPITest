package com.fukuchi.restapitestproject.usecase.module

import com.fukuchi.restapitestproject.usecase.RestApiTestUseCase
import com.fukuchi.restapitestproject.usecase.impl.RestApiTestUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Usecaseのhilt注入
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindRestApiTestUseCase(impl: RestApiTestUseCaseImpl): RestApiTestUseCase
}