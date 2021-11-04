package com.dalvikmx.moviehunter.data.remote

import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse

interface IRemoteDataSource {
    suspend fun requestPopularMovieList(): Resource<MoviesListResponse>
    suspend fun requestTopRatedMovieList(): Resource<MoviesListResponse>
}