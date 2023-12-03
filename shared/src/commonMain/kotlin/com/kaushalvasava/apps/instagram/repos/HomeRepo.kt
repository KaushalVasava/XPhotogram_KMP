package com.kaushalvasava.apps.instagram.repos

import com.kaushalvasava.apps.instagram.api.InstagramApiImpl
import com.kaushalvasava.apps.instagram.models.BaseResponse
import com.kaushalvasava.apps.instagram.models.Notification
import com.kaushalvasava.apps.instagram.models.Post
import com.kaushalvasava.apps.instagram.models.Story
import com.kaushalvasava.apps.instagram.models.Tweet
import com.kaushalvasava.apps.instagram.models.User
import com.kaushalvasava.apps.instagram.util.DemoData
import com.kaushalvasava.apps.instagram.util.DemoData.tweets
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlin.random.Random

class HomeRepo(private val instagramApiImpl: InstagramApiImpl) {
    private val tweetList = mutableListOf<Tweet>()

    suspend fun getUserResponse(): BaseResponse<User> {
        return instagramApiImpl.getUsers()
    }

    suspend fun getPosts(): List<Post> {
        return instagramApiImpl.getPosts().data
    }

    suspend fun getStories(): List<Story> {
        return instagramApiImpl.getStories().data
    }

    suspend fun getNotifications(): List<Notification> {
        return instagramApiImpl.getNotifications().data
    }

    suspend fun getTweets(): List<Tweet> {
        delay(5000L)
        for (i in 1..30) {
            tweetList.add(
                Tweet(
                    description = tweets.random(),
                    userId = "",
                    comments = tweets.subList(0, Random.nextInt(1, 4)),
                    likeCount = i * i,
                    retweetCount = i * 2,
                    bookmarkCount = 10 * i,
                    imageUrl = DemoData.urls.subList(0, 2),
                    timeStamp = Clock.System.now().epochSeconds
                )
            )
        }
        return tweetList
    }
}