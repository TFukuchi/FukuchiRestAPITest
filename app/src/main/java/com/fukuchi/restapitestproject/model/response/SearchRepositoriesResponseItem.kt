package com.fukuchi.restapitestproject.model.response

import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponseItem(
    @SerializedName("name")
    var name: String,

    @SerializedName("full_name")
    val fullName: String
)