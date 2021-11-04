package com.dalvikmx.moviehunter.presentation.features.billboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.dalvikmx.moviehunter.domain.IMovieDataRepository
import com.dalvikmx.moviehunter.presentation.base.BaseViewModel
import com.dalvikmx.moviehunter.utilities.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class BillboardViewModel @Inject
constructor(
    private val dataRepositoryRepository: IMovieDataRepository
) : BaseViewModel() {

    private val moviesLiveDataPrivate = MutableLiveData<Resource<MoviesListResponse>>()
    val moviesLiveData: LiveData<Resource<MoviesListResponse>> get() = moviesLiveDataPrivate

    private val openMovieDetailsPrivate = MutableLiveData<SingleEvent<MovieResponse>>()
    val openMovieDetails: LiveData<SingleEvent<MovieResponse>> get() = openMovieDetailsPrivate

    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    fun getPopularList() {
        viewModelScope.launch {
            moviesLiveDataPrivate.value = Resource.Loading()
            dataRepositoryRepository.requestPopularMoviesList().collect {
                moviesLiveDataPrivate.value = it
            }
        }
    }

    fun getTopRatedList() {
        viewModelScope.launch {
            moviesLiveDataPrivate.value = Resource.Loading()
            dataRepositoryRepository.requestTopRatedMoviesList().collect {
                moviesLiveDataPrivate.value = it
            }
        }
    }

    fun openMovieDetails(movieSelected: MovieResponse) {
        openMovieDetailsPrivate.value = SingleEvent(movieSelected)
    }

    fun showMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showSnackBarPrivate.value = SingleEvent(error.description)
    }
}