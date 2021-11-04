package com.dalvikmx.moviehunter.di

import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import com.dalvikmx.moviehunter.utilities.Constants.API_KEY
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
class FeatureToggleModule {
    @Provides
    @Singleton
    fun provideFeatureToggling(firebaseRemoteConfig: FirebaseRemoteConfig?): FeatureToggling {
        return FeatureToggling(apiKey = firebaseRemoteConfig?.get(API_KEY)?.asString() ?: "")
    }

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }
}
