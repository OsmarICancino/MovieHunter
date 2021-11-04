package com.dalvikmx.moviehunter.data

import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.dalvikmx.moviehunter.data.remote.RemoteData
import com.dalvikmx.moviehunter.domain.IMovieDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieDataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) :
    IMovieDataRepository {

    override suspend fun requestPopularMoviesList(): Flow<Resource<MoviesListResponse>> {
        return flow {
            emit(remoteRepository.requestPopularMovieList())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestTopRatedMoviesList(): Flow<Resource<MoviesListResponse>> {
        return flow {
            emit(remoteRepository.requestTopRatedMovieList())
        }.flowOn(ioDispatcher)
    }

}