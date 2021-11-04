package com.dalvikmx.moviehunter.presentation.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import com.dalvikmx.moviehunter.databinding.ActivitySplashBinding
import com.dalvikmx.moviehunter.presentation.base.BaseActivity
import com.dalvikmx.moviehunter.presentation.features.billboard.BillboardActivity
import com.dalvikmx.moviehunter.utilities.extensions.goToActivity
import com.dalvikmx.moviehunter.utilities.extensions.observe
import com.dalvikmx.moviehunter.utilities.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    private val binding by viewBinding(
        ActivitySplashBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFeatureToggling()
    }

    override fun observeViewModel() {
        observe(viewModel.featureTogglingLiveData, ::handleSplashScreen)
    }

    override fun initViewBinding() {
        setContentView(binding.root)
    }

    private fun handleSplashScreen(status: Resource<FeatureToggling>) {
        if (status is Resource.Success) {
            status.data?.let {
                it.apiKey.run {
                    goToActivity(Intent(this@SplashActivity, BillboardActivity::class.java))
                    finish()
                }
            }
        }
    }
}