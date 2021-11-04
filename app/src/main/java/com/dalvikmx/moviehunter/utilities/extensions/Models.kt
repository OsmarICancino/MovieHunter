package com.dalvikmx.moviehunter.utilities.extensions

import android.content.res.Resources
import android.util.DisplayMetrics
import com.dalvikmx.moviehunter.BuildConfig
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse

private const val POSTER_SIZE_1 = "w92/"
private const val POSTER_SIZE_2 = "w154/"
private const val POSTER_SIZE_3 = "w185/"
private const val POSTER_SIZE_4 = "w342/"
private const val POSTER_SIZE_5 = "w500/"
private const val POSTER_SIZE_6 = "w780/"

fun MovieResponse.getUrlImage(): String {
    return BuildConfig.MOVIE_IMAGE_ENDPOINT + getSizedURL() + posterPath + "?api_key=42f6037782ffa6d0229e74706d3206de"
}

/**
 * This function modifies the image URL in order to retrieve the best fit size for the
 * user's screen. :)
 */
fun getSizedURL(): String {
    return when (Resources.getSystem().displayMetrics.densityDpi) {
        DisplayMetrics.DENSITY_LOW -> {
            POSTER_SIZE_1
        }
        DisplayMetrics.DENSITY_MEDIUM -> {
            POSTER_SIZE_2
        }
        DisplayMetrics.DENSITY_HIGH, DisplayMetrics.DENSITY_260, DisplayMetrics.DENSITY_280, DisplayMetrics.DENSITY_300 -> {
            POSTER_SIZE_3
        }
        DisplayMetrics.DENSITY_XHIGH, DisplayMetrics.DENSITY_340, DisplayMetrics.DENSITY_360, DisplayMetrics.DENSITY_400 -> {
            POSTER_SIZE_4
        }
        DisplayMetrics.DENSITY_XXHIGH, DisplayMetrics.DENSITY_420, DisplayMetrics.DENSITY_440, DisplayMetrics.DENSITY_560 -> {
            POSTER_SIZE_5
        }
        DisplayMetrics.DENSITY_XXXHIGH -> {
            POSTER_SIZE_6
        }
        else -> {
            POSTER_SIZE_4
        }
    }
}