package com.playerthong.androidnews.api

import androidx.annotation.Keep

@Keep
data class ErrorResponse(
    val message: String? = null,
    val code: Any? = null
)