package com.dalvikmx.moviehunter.presentation.features.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject
constructor() : BaseViewModel() {

    private val movieDetailPrivate = MutableLiveData<MovieResponse>()
    val movieDetailData: LiveData<MovieResponse> get() = movieDetailPrivate

    fun initIntentData(movieDetail: MovieResponse?) {
        movieDetail?.run {
            movieDetailPrivate.value = this
        }
    }
}