package com.dalvikmx.moviehunter.presentation.features.billboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import com.dalvikmx.moviehunter.R
import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.dalvikmx.moviehunter.databinding.ActivityMainBinding
import com.dalvikmx.moviehunter.presentation.base.BaseActivity
import com.dalvikmx.moviehunter.presentation.features.billboard.adapter.GridItemDecoration
import com.dalvikmx.moviehunter.presentation.features.moviedetail.MovieDetailActivity
import com.dalvikmx.moviehunter.utilities.Constants.MOVIE_ITEM_KEY
import com.dalvikmx.moviehunter.utilities.SingleEvent
import com.dalvikmx.moviehunter.utilities.extensions.goToActivity
import com.dalvikmx.moviehunter.utilities.extensions.invisible
import com.dalvikmx.moviehunter.utilities.extensions.loadBillboard
import com.dalvikmx.moviehunter.utilities.extensions.observe
import com.dalvikmx.moviehunter.utilities.extensions.observeEvent
import com.dalvikmx.moviehunter.utilities.extensions.setupSnackbar
import com.dalvikmx.moviehunter.utilities.extensions.show
import com.dalvikmx.moviehunter.utilities.extensions.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

const val SPACE_PIXELS = 10

@AndroidEntryPoint
class BillboardActivity : BaseActivity() {

    private val viewModel: BillboardViewModel by viewModels()

    private val binding by viewBinding(
        ActivityMainBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPopularList()
    }

    private fun handleMoviesList(status: Resource<MoviesListResponse>) {
        binding.run {
            when (status) {
                is Resource.Loading -> progressView.show()
                is Resource.Success -> status.data?.let {
                    it.results?.run {
                        billboardRecycler.loadBillboard(this@BillboardActivity, viewModel, this)
                        billboardRecycler.show()
                        progressView.invisible()
                        errorMessage.invisible()
                    }
                }
                is Resource.DataError -> {
                    errorMessage.show()
                    progressView.invisible()
                    status.errorCode?.let { viewModel.showMessage(it) }
                }
            }
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<MovieResponse>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ITEM_KEY, it)
            }
            goToActivity(nextScreenIntent)
        }
    }

    override fun observeViewModel() {
        viewModel.run {
            observe(moviesLiveData, ::handleMoviesList)
            observeEvent(openMovieDetails, ::navigateToDetailsScreen)
            observeSnackBarMessages(showSnackBar)
        }
    }

    override fun initViewBinding() {
        installSplashScreen()
        setContentView(binding.root)
        binding.run {
            toolbarComponent.setup(this@BillboardActivity, getString(R.string.movie_hunter_title))
            toolbarComponent.backButtonVisibility(false)
            bottomNavigationComponent.setup(viewModel)
            billboardRecycler.addItemDecoration(GridItemDecoration(SPACE_PIXELS))
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }


}