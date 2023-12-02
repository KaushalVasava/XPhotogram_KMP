package com.kaushalvasava.apps.instagram.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val type: String,
    val data: List<T>,
)