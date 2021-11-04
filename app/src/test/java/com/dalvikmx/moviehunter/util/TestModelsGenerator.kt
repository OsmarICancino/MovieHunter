package com.dalvikmx.moviehunter.util

import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.google.gson.Gson
import java.io.File


class TestModelsGenerator {
    private var movies: MoviesListResponse = MoviesListResponse()

    init {
        val jsonString = getJson("success_response.json")
        movies = Gson().fromJson(jsonString, MoviesListResponse::class.java)
        print("this is $movies")
    }

    fun generateMovieList(): MoviesListResponse {
        return movies
    }

    fun generateMovieModelWithEmptyList(): MoviesListResponse {
        return MoviesListResponse().apply { results = listOf() }
    }

    fun generateMoviesItemModel(): MovieResponse? {
        return movies.results?.first()
    }

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path ?: "")
        return String(file.readBytes())
    }
}
