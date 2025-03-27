package com.fukuchi.restapitestproject.api

import com.fukuchi.restapitestproject.api.service.RestSearchApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RestSearchApiClient @Inject constructor() {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchApiService: RestSearchApiService by lazy { retrofit.create(RestSearchApiService::class.java) }
}