package com.dalvikmx.moviehunter.utilities.extensions

import com.dalvikmx.moviehunter.BuildConfig

fun String.getUrlPosterImage(): String {
    return BuildConfig.MOVIE_IMAGE_ENDPOINT + getSizedURL() + this + "?api_key=42f6037782ffa6d0229e74706d3206de"
}