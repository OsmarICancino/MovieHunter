package com.dalvikmx.moviehunter.domain

import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import kotlinx.coroutines.flow.Flow

interface IFeatureToggleRepository {
    suspend fun requestFeatureToggling(): Flow<Resource<FeatureToggling>>
}