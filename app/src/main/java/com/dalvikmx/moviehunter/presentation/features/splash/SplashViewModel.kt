package com.dalvikmx.moviehunter.presentation.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dalvikmx.moviehunter.domain.IFeatureToggleRepository
import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.models.containers.FeatureToggling
import com.dalvikmx.moviehunter.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject
constructor(private val dataRepositoryRepository: IFeatureToggleRepository) : BaseViewModel() {

    private val featureTogglingLiveDataPrivate = MutableLiveData<Resource<FeatureToggling>>()
    val featureTogglingLiveData: LiveData<Resource<FeatureToggling>> get() = featureTogglingLiveDataPrivate

    fun getFeatureToggling() {
        viewModelScope.launch {
            featureTogglingLiveDataPrivate.value = Resource.Loading()
            dataRepositoryRepository.requestFeatureToggling().collect {
                featureTogglingLiveDataPrivate.value = it
            }
        }
    }

}