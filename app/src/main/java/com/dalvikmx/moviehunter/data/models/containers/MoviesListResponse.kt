package com.dalvikmx.moviehunter.data.models.containers

import com.google.gson.annotations.SerializedName

class MoviesListResponse {
    @SerializedName("page")
    val page: String? = null

    @SerializedName("total_pages")
    val totalPages: String? = null

    @SerializedName("total_results")
    val totalResults: String? = null

    @SerializedName("results")
    var results: List<MovieResponse>? = null
}