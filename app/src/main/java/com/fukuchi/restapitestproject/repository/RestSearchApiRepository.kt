package com.fukuchi.restapitestproject.repository

import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponse

/**
 * RepositoryのInterface
 */
interface RestSearchApiRepository {
    suspend fun getSearchRepository(queryName: String) : SearchRepositoriesResponse?
}