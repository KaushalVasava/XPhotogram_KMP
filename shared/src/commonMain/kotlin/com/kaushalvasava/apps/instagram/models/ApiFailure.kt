package com.kaushalvasava.apps.instagram.models

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiFailure {
    data class Unknown(val error: String) : ApiFailure()
}
