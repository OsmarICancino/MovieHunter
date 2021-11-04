package com.dalvikmx.moviehunter.data.errorhandler.mapper

interface ErrorMapperSource {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}
