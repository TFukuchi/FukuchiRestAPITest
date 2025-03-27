package com.fukuchi.restapitestproject.usecase.impl

import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponse
import com.fukuchi.restapitestproject.repository.RestSearchApiRepository
import com.fukuchi.restapitestproject.usecase.RestApiTestUseCase
import javax.inject.Inject

/**
 * UseCaseのImpl Class
 * repositoryはhiltから取得
 */
class RestApiTestUseCaseImpl @Inject constructor(
    private val repository: RestSearchApiRepository
): RestApiTestUseCase {
    override suspend fun getRepository(query: String): SearchRepositoriesResponse? {
        return repository.getSearchRepository(query)
    }
}