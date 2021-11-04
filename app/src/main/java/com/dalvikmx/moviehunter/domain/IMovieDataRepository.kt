package com.dalvikmx.moviehunter.domain

import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import kotlinx.coroutines.flow.Flow

interface IMovieDataRepository {
    suspend fun requestPopularMoviesList(): Flow<Resource<MoviesListResponse>>
    suspend fun requestTopRatedMoviesList(): Flow<Resource<MoviesListResponse>>
}