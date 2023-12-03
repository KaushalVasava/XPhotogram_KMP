package com.kaushalvasava.apps.instagram.api

import com.kaushalvasava.apps.instagram.models.BaseResponse
import com.kaushalvasava.apps.instagram.models.Notification
import com.kaushalvasava.apps.instagram.models.Post
import com.kaushalvasava.apps.instagram.models.Story
import com.kaushalvasava.apps.instagram.models.User

interface InstagramApi {
    suspend fun getUsers(): BaseResponse<User>

    suspend fun getPosts(): BaseResponse<Post>

    suspend fun getStories(): BaseResponse<Story>

    suspend fun getNotifications(): BaseResponse<Notification>
}


