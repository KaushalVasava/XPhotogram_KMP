package com.kaushalvasava.apps.instagram.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("bio")
    val bio: String,
    @SerialName("links")
    val links: List<String>,
    @SerialName("followerIds")
    val followerIds: List<String>,
    @SerialName("followingIds")
    val followingIds: List<String>,
    @SerialName("postIds")
    val postIds: List<String>,
    @SerialName("storyIds")
    val storyIds: List<String>
)
