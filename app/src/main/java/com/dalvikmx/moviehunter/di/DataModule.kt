package com.dalvikmx.moviehunter.di

import com.dalvikmx.moviehunter.data.FeatureToggleRepository
import com.dalvikmx.moviehunter.domain.IFeatureToggleRepository
import com.dalvikmx.moviehunter.data.MovieDataRepository
import com.dalvikmx.moviehunter.domain.IMovieDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(movieDataRepository: MovieDataRepository): IMovieDataRepository

    @Binds
    @Singleton
    abstract fun provideToggleDataRepository(movieDataRepository: FeatureToggleRepository): IFeatureToggleRepository
}
