package com.dalvikmx.moviehunter.presentation.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dalvikmx.moviehunter.utilities.extensions.getUrlPosterImage
import com.dalvikmx.moviehunter.utilities.extensions.loadImageFromUrl

object MovieDetailBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadTrailerImage(view: ImageView, url: String?) {
        view.loadImageFromUrl(view.context, url?.getUrlPosterImage())
    }
}