package com.fukuchi.restapitestproject.model.response

import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponse(
    @SerializedName("items")
    val items: List<SearchRepositoriesResponseItem>
)