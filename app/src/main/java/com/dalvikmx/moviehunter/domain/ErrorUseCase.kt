package com.dalvikmx.moviehunter.domain

interface ErrorUseCase {
    fun getError(errorCode: Int): com.dalvikmx.moviehunter.data.errorhandler.Error
}