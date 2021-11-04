package com.dalvikmx.moviehunter.domain

import com.dalvikmx.moviehunter.data.errorhandler.mapper.ErrorMapper
import com.dalvikmx.moviehunter.data.errorhandler.Error
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
