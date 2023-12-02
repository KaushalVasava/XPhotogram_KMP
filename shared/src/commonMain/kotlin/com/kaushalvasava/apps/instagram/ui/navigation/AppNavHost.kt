package com.kaushalvasava.apps.instagram.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kaushalvasava.apps.instagram.ui.screen.ChatListScreen
import com.kaushalvasava.apps.instagram.ui.screen.ChatScreen
import com.kaushalvasava.apps.instagram.ui.screen.CreatePostScreen
import com.kaushalvasava.apps.instagram.ui.screen.CreateTweetScreen
import com.kaushalvasava.apps.instagram.ui.screen.HomeScreen
import com.kaushalvasava.apps.instagram.ui.screen.NotificationScreen
import com.kaushalvasava.apps.instagram.ui.screen.ProfileScreen
import com.kaushalvasava.apps.instagram.ui.screen.ReelsScreen
import com.kaushalvasava.apps.instagram.ui.screen.SearchScreen
import com.kaushalvasava.apps.instagram.ui.screen.SplashScreen
import com.kaushalvasava.apps.instagram.ui.screen.TweetListScreen
import com.kaushalvasava.apps.instagram.ui.screen.TweetScreen
import com.kaushalvasava.apps.instagram.ui.screen.UserFollowListScreen
import com.kaushalvasava.apps.instagram.ui.screen.ViewPostScreen
import com.kaushalvasava.apps.instagram.ui.screen.ViewStory
import com.kaushalvasava.apps.instagram.util.AppConstants.MY_USER_ID
import com.kaushalvasava.apps.instagram.viewmodel.HomeViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    navController: Navigator,
    modifier: Modifier = Modifier,
) {

    var isSplashScreenFinished by rememberSaveable {
        mutableStateOf(false)
    }
    NavHost(
        navigator = navController,
        navTransition = NavTransition(),
        modifier = modifier,
        initialRoute = if (isSplashScreenFinished) {
            NavigationItem.Home.route
        } else {
            NavigationItem.Splash.route
        }
    ) {
        scene(
            NavigationItem.Splash.route,
            navTransition = NavTransition()
        ) {
            SplashScreen {
                navController.navigate(NavigationItem.Home.route)
                isSplashScreenFinished = true
            }
        }
        scene(
            NavigationItem.Home.route,
            navTransition = NavTransition()
        ) {
            HomeScreen(homeViewModel = homeViewModel, navController = navController)
        }
        scene(
            NavigationItem.Search.route,
            navTransition = NavTransition()
        ) {
            SearchScreen(homeViewModel = homeViewModel, navController = navController)
        }
        scene(NavigationItem.CreatePost.route) {
            CreatePostScreen()
        }
        scene(
            NavigationItem.TweetList.route,
            navTransition = NavTransition()
        ) {
            TweetListScreen(homeViewModel, navController)
        }
        scene(
            "${NavigationItem.Tweet.route}/{tweetId}"
        ) {
            val tweetId = it.path<String>("tweetId")
            tweetId?.let { id ->
                TweetScreen(id, homeViewModel, navController)
            }
        }
        scene(NavigationItem.CreateTweet.route) {
            CreateTweetScreen(homeViewModel = homeViewModel, navController = navController)
        }
        scene(NavigationItem.Reels.route) {
            ReelsScreen(homeViewModel = homeViewModel, navController)
        }
        scene(NavigationItem.ChatList.route) {
            ChatListScreen(homeViewModel, navController = navController)
        }
        scene(
            "${NavigationItem.Followers.route}/{isFollowing}/{userId}"
        ) {
            val isFollower = it.path<Boolean>("isFollowing") ?: false
            val userId = it.path<String>("userId") ?: MY_USER_ID
            UserFollowListScreen(
                homeViewModel = homeViewModel,
                isFollowing = isFollower,
                userId = userId,
                navController = navController
            )
        }
        scene("${NavigationItem.Chat.route}/{userId}") {
            val userId = it.path<String>("userId") ?: "userid"
            ChatScreen(userId, homeViewModel = homeViewModel, navController = navController)
        }
        scene(
            "${NavigationItem.Profile.route}/{userid}"
        ) {
            val userId = it.path<String>("userid")
            ProfileScreen(
                userId = userId ?: MY_USER_ID,
                homeViewModel = homeViewModel,
                navController = navController
            )
        }
        scene("${NavigationItem.ViewPost.route}/{postId}") {
            val postId = it.path<String>("postId")
            postId?.let { id ->
                ViewPostScreen(id, homeViewModel = homeViewModel, navController = navController)
            }
        }
        scene("${NavigationItem.ViewStory.route}/{storyId}/{userId}") {
            val storyId = it.path<String>("storyId")
            val userId = it.path<String>("userId")
            if (storyId != null && userId != null) {
                ViewStory(
                    storyId,
                    userId,
                    homeViewModel = homeViewModel,
                    navController = navController
                )
            }
        }
        scene(NavigationItem.Notification.route) {
            NotificationScreen(homeViewModel = homeViewModel, navController = navController)
        }
    }
}