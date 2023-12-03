package com.kaushalvasava.apps.instagram.api

import com.kaushalvasava.apps.instagram.models.BaseResponse
import com.kaushalvasava.apps.instagram.models.Notification
import com.kaushalvasava.apps.instagram.models.Post
import com.kaushalvasava.apps.instagram.models.Story
import com.kaushalvasava.apps.instagram.models.User
import com.kaushalvasava.apps.instagram.util.AppConstants
import io.ktor.client.call.body
import io.ktor.client.request.get

class InstagramApiImpl: InstagramApi {
    override suspend fun getUsers(): BaseResponse<User> {
        return KtorModule.httpClient.get("${AppConstants.BASE_URL}/users").body()
    }

    override suspend fun getPosts(): BaseResponse<Post> {
        return KtorModule.httpClient.get("${AppConstants.BASE_URL}/posts")
            .body()
    }

    override suspend fun getStories(): BaseResponse<Story> {
        return KtorModule.httpClient.get("${AppConstants.BASE_URL}/stories")
            .body()
    }

    override suspend fun getNotifications(): BaseResponse<Notification> {
        return KtorModule.httpClient.get("${AppConstants.BASE_URL}/notifications")
            .body()
    }
}