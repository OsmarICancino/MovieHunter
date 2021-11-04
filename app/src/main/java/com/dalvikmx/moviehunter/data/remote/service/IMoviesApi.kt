package com.dalvikmx.moviehunter.data.remote.service

import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.dalvikmx.moviehunter.utilities.Constants.DEFAULT_REGION
import java.util.Locale
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesApi {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") key: String,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("page") page: Int = 1,
        @Query("region") region: String = DEFAULT_REGION
    ): Response<MoviesListResponse>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") key: String,
        @Query("language") language: String = Locale.getDefault().language,
        @Query("page") page: Int = 1,
        @Query("region") region: String = DEFAULT_REGION
    ): Response<MoviesListResponse>
}