package com.dalvikmx.moviehunter.presentation

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable? = null) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}