package com.playerthong.androidnews.api

import androidx.annotation.Keep

@Keep
data class ApiResponse<T>(
    val data: T?,
    val isSuccess: Boolean = true,
    val message: String? = null,
    val error: ErrorResponse? = null,
    val errorCode: Int? = null
) {
    companion object {
        var ERROR_NULL=0;
        var ERROR_UNSUCCESS=1;
        fun <T> ERROR(errorCode: Int? = null, message: String? = null, error:ErrorResponse? = null) =
            ApiResponse<T>(data = null, isSuccess = false, errorCode = errorCode, message = message, error = error)
    }
}