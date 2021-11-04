package com.dalvikmx.moviehunter.presentation.features.billboard.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.databinding.MovieItemBinding
import com.dalvikmx.moviehunter.presentation.listeners.RecyclerItemListener
import com.dalvikmx.moviehunter.utilities.extensions.getUrlImage
import com.dalvikmx.moviehunter.utilities.extensions.loadRoundedImageFromUrl

class MovieViewHolder(
    private val itemBinding: MovieItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movieItem: MovieResponse, recyclerItemListener: RecyclerItemListener) {
        itemBinding.posterContainer.run {
            loadRoundedImageFromUrl(context, movieItem.getUrlImage())
            setOnClickListener {
                recyclerItemListener.onItemSelected(movieItem)
            }
        }
    }
}