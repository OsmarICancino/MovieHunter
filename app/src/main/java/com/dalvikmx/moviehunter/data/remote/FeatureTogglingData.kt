package com.dalvikmx.moviehunter.data.remote

import android.util.Log
import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import com.dalvikmx.moviehunter.utilities.Constants
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject


class FeatureTogglingData @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : IFeatureTogglingDataSource {

    override suspend fun requestFeatureToggles(): Resource<FeatureToggling> {
        val featureToggling = FeatureToggling()
        firebaseRemoteConfig.let {
            it.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(FirebaseRemoteConfig.TAG, "Config params updated: ${task.result}")
                    } else {
                        Log.d(FirebaseRemoteConfig.TAG, "Config params fetch failed")
                    }
                    val featureTogglingValue = it.getString(Constants.API_KEY)
                    featureToggling.apiKey = featureTogglingValue
                }
        }
        return Resource.Success(data = featureToggling)
    }
}