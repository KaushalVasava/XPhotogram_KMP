package com.kaushalvasava.apps.instagram.models

import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val senderImage: String,
    val receiverImage: String,
    val msgs: List<Message>,
)
