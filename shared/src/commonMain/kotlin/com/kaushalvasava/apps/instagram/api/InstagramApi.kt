package com.kaushalvasava.apps.instagram.api

import com.kaushalvasava.apps.instagram.api.KtorModule.Companion.httpClient
import com.kaushalvasava.apps.instagram.models.BaseResponse
import com.kaushalvasava.apps.instagram.models.Notification
import com.kaushalvasava.apps.instagram.models.Post
import com.kaushalvasava.apps.instagram.models.Story
import com.kaushalvasava.apps.instagram.models.User
import com.kaushalvasava.apps.instagram.util.AppConstants
import io.ktor.client.call.body
import io.ktor.client.request.get

object InstagramApi {
    suspend fun getUsers(): BaseResponse<User> {
        return httpClient.get("${AppConstants.BASE_URL}/users").body()
    }

    suspend fun getPosts(): BaseResponse<Post> {
        return httpClient.get("${AppConstants.BASE_URL}/posts")
            .body()
    }

    suspend fun getStories(): BaseResponse<Story> {
        return httpClient.get("${AppConstants.BASE_URL}/stories")
            .body()
    }

    suspend fun getNotifications(): BaseResponse<Notification> {
        return httpClient.get("${AppConstants.BASE_URL}/notifications")
            .body()
    }
}


