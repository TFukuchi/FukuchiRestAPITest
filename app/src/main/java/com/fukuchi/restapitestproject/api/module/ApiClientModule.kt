package com.fukuchi.restapitestproject.api.module

import com.fukuchi.restapitestproject.api.RestSearchApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ApiClientのHilt注入
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiClientModule {

    /**
     * RestSearchApiClientを作成
     * Retrofitを複数作られるのを避けるためsingleton
     */
    @Provides
    @Singleton
    fun provideRestSearchApiClient(): RestSearchApiClient {
        return RestSearchApiClient()
    }
}