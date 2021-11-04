package com.dalvikmx.moviehunter.presentation.features.billboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.databinding.MovieItemBinding
import com.dalvikmx.moviehunter.presentation.features.billboard.BillboardViewModel
import com.dalvikmx.moviehunter.presentation.listeners.RecyclerItemListener

class MovieAdapter(private val moviesListViewModel: BillboardViewModel, private val movies: List<MovieResponse>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(movieSelected: MovieResponse) {
            moviesListViewModel.openMovieDetails(movieSelected)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // inflate the custom view from xml layout file
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // return the view holder
        return MovieViewHolder(itemBinding)

    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // display the current animal
        holder.bind(movies[position], onItemClickListener)
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return movies.size
    }
}