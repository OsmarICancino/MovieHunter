package com.dalvikmx.moviehunter.data.remote

import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.errorhandler.NETWORK_ERROR
import com.dalvikmx.moviehunter.data.errorhandler.NO_INTERNET_CONNECTION
import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.dalvikmx.moviehunter.data.remote.service.IMoviesApi
import com.dalvikmx.moviehunter.utilities.NetworkHandler
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class RemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkHandler,
    private val featureToggling: FeatureToggling
) : IRemoteDataSource {
    override suspend fun requestPopularMovieList(): Resource<MoviesListResponse> {
        val moviesService = serviceGenerator.createService(IMoviesApi::class.java)
        return when (val response = processCall((moviesService::getPopular))) {
            is MoviesListResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestTopRatedMovieList(): Resource<MoviesListResponse> {
        val moviesService = serviceGenerator.createService(IMoviesApi::class.java)
        return when (val response = processCall((moviesService::getTopRated))) {
            is MoviesListResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend (value: String) -> Response<*>): Any? {
        if (!networkConnectivity.isNetworkAvailable()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke(featureToggling.apiKey)
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}