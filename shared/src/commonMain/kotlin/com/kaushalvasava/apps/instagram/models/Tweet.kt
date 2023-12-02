package com.kaushalvasava.apps.instagram.models

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: String = Clock.System.now().toString(),
    val description: String,
    val userId: String,
    val comments: List<String> = emptyList(),
    val likeCount: Int = 0,
    val retweetCount: Int = 0,
    val bookmarkCount: Int = 0,
    val imageUrl: List<String> = emptyList(),
    val timeStamp: Long = Clock.System.now().toEpochMilliseconds()
)
