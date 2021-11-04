package com.dalvikmx.moviehunter.utilities.extensions

import android.app.Service
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.presentation.features.billboard.BillboardViewModel
import com.dalvikmx.moviehunter.presentation.features.billboard.adapter.GridItemDecoration
import com.dalvikmx.moviehunter.presentation.features.billboard.adapter.MovieAdapter
import com.dalvikmx.moviehunter.utilities.ImageUtils.getPlaceHolder
import com.dalvikmx.moviehunter.utilities.SingleEvent
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadRoundedImageFromUrl(context: Context, url: String?) {
    url?.run {
        Glide.with(context)
            .asBitmap()
            .load(this)
            .placeholder(getPlaceHolder(context))
            .apply(RequestOptions().transform(RoundedCorners(15)))
            .into(this@loadRoundedImageFromUrl)
    }
}

fun ImageView.loadImageFromUrl(context: Context, url: String?) {
    url?.run {
        Glide.with(context)
            .asBitmap()
            .load(this)
            .placeholder(getPlaceHolder(context))
            .into(this@loadImageFromUrl)
    }
}

fun RecyclerView.loadBillboard(context: Context, viewModel: BillboardViewModel, moviesList: List<MovieResponse>) {
    GridLayoutManager(
        context, // context
        3, // span count
        RecyclerView.VERTICAL, // orientation
        false // reverse layout
    ).apply {
        // specify the layout manager for recycler view
        this@loadBillboard.layoutManager = this
    }
    this.adapter = MovieAdapter(viewModel, moviesList)
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<SingleEvent<Any>>,
    timeLength: Int) {
    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            when (it) {
                is String -> {
                    hideKeyboard()
                    showSnackbar(it, timeLength)
                }
                is Int -> {
                    hideKeyboard()
                    showSnackbar(this.context.getString(it), timeLength)
                }
                else -> {
                }
            }

        }
    })
}

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}