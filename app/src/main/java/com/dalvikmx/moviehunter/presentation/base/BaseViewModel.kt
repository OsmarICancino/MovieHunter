package com.dalvikmx.moviehunter.presentation.base

import androidx.lifecycle.ViewModel
import com.dalvikmx.moviehunter.domain.ErrorManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    /**
     * Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}