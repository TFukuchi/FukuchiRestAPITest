package com.fukuchi.restapitestproject.api.service

import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestSearchApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchRepositoriesResponse?
}