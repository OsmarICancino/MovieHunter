package com.dalvikmx.moviehunter.data

import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import com.dalvikmx.moviehunter.data.remote.FeatureTogglingData
import com.dalvikmx.moviehunter.domain.IFeatureToggleRepository
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FeatureToggleRepository @Inject constructor(private val remoteRepository: FeatureTogglingData, private val ioDispatcher: CoroutineContext) :
    IFeatureToggleRepository {
    override suspend fun requestFeatureToggling(): Flow<Resource<FeatureToggling>> {
        return flow {
            emit(remoteRepository.requestFeatureToggles())
        }.flowOn(ioDispatcher)
    }

}