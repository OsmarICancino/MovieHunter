package com.dalvikmx.moviehunter.data.models.containers

import com.google.gson.annotations.SerializedName
import kotlinx.datetime.toLocalDate
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieResponse: Serializable {
    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("original_title")
    val originalTitle: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("video")
    val video: String? = null

    @SerializedName("vote_average")
    val voteAverage: String? = null

    val rating: String
        get() = "$voteAverage/10"

    val year: String
    get() {
        return releaseDate?.toLocalDate()?.year?.toString() ?: ""
    }
}