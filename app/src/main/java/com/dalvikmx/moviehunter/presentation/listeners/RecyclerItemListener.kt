package com.dalvikmx.moviehunter.presentation.listeners

import com.dalvikmx.moviehunter.data.models.containers.MovieResponse

interface RecyclerItemListener {
    fun onItemSelected(movieSelected: MovieResponse)
}