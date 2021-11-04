package com.dalvikmx.moviehunter.presentation.features.moviedetail

import android.os.Bundle
import androidx.activity.viewModels
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.databinding.ActivityMovieDetailBinding
import com.dalvikmx.moviehunter.presentation.base.BaseActivity
import com.dalvikmx.moviehunter.utilities.Constants.MOVIE_ITEM_KEY
import com.dalvikmx.moviehunter.utilities.extensions.viewBinding

class MovieDetailActivity : BaseActivity() {

    private val mViewModel: MovieDetailViewModel by viewModels()

    private val binding by viewBinding(
        ActivityMovieDetailBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.initIntentData(intent.getSerializableExtra(MOVIE_ITEM_KEY) as? MovieResponse)
    }

    override fun observeViewModel() {
        binding.viewModel = mViewModel
    }

    override fun initViewBinding() {
        setContentView(binding.root)
        binding.run {
            lifecycleOwner = this@MovieDetailActivity
            mdHeader.btnBack.setOnClickListener { onBackPressed() }
        }
    }
}