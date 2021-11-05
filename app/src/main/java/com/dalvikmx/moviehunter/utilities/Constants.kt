package com.dalvikmx.moviehunter.utilities

import com.dalvikmx.moviehunter.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get

object Constants {
    const val BASE_URL = BuildConfig.MOVIES_DB_ENDPOINT
    const val DEFAULT_REGION = "US"
    const val MOVIE_ITEM_KEY = "MOVIE_ITEM_KEY"
    const val API_KEY = "api_key"
    val apiKey: String by lazy { FirebaseRemoteConfig.getInstance()[API_KEY].asString()}
}