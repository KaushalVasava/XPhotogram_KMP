package com.kaushalvasava.apps.instagram.models

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val msg: String,
    val timeStamp: String,
    val isSeen: Boolean = false,
    val userId: String,
)
