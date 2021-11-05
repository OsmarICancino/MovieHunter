package com.dalvikmx.moviehunter.utilities.extensions

import com.dalvikmx.moviehunter.BuildConfig
import com.dalvikmx.moviehunter.utilities.Constants.apiKey

fun String.getUrlPosterImage(): String {
    return BuildConfig.MOVIE_IMAGE_ENDPOINT + getSizedURL() + this + "?api_key=${apiKey}"
}