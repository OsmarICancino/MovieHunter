package com.dalvikmx.moviehunter.data.remote

import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling

internal interface IFeatureTogglingDataSource {
    suspend fun requestFeatureToggles(): Resource<FeatureToggling>
}