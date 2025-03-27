package com.fukuchi.restapitestproject.repository.impl

import com.fukuchi.restapitestproject.api.RestSearchApiClient
import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponse
import com.fukuchi.restapitestproject.repository.RestSearchApiRepository
import javax.inject.Inject

/**
 * RepositoryのImpl class.
 * RestApiClientはHiltから取得
 */
class RestSearchApiRepositoryImpl @Inject constructor(
    private val apiClient: RestSearchApiClient): RestSearchApiRepository {
    override suspend fun getSearchRepository(queryName: String) : SearchRepositoriesResponse? {
        return apiClient.searchApiService.searchRepositories(queryName)
    }
}