package com.kaushalvasava.apps.instagram

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RepeatOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.kaushalvasava.apps.instagram.ui.components.BottomNavItem
import com.kaushalvasava.apps.instagram.ui.components.BottomNavigationBar
import com.kaushalvasava.apps.instagram.ui.navigation.AppNavHost
import com.kaushalvasava.apps.instagram.ui.navigation.NavigationItem
import com.kaushalvasava.apps.instagram.ui.navigation.Screen
import com.kaushalvasava.apps.instagram.util.AppConstants.MY_USER_ID
import com.kaushalvasava.apps.instagram.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.route.Route


@Composable
fun App() {
    PreComposeApp {
        MaterialTheme {
            val navController = rememberNavigator()
            val mainViewModel = HomeViewModel()

            val screens = listOf(
                NavigationItem.Home.route,
                NavigationItem.Search.route,
                NavigationItem.CreatePost.route,
                NavigationItem.TweetList.route,
                "${NavigationItem.Profile.route}/{userid}"
            )
            val entry by navController.currentEntry.collectAsState(null)
            val currentRoute:String = (entry as? BackStackEntry)?.route?.route ?:""
            val isBottomNavVisible:Boolean = screens.contains(currentRoute)
            Scaffold(
                bottomBar = {
                    AnimatedVisibility(
                        visible = isBottomNavVisible,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .background(MaterialTheme.colors.background)
                                .fillMaxWidth()
                        ) {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        NavigationItem.Home.route,
                                        Screen.HOME.name,
                                        icon = rememberVectorPainter(image = Icons.Default.Home)
                                    ),
                                    BottomNavItem(
                                        NavigationItem.Search.route,
                                        Screen.SEARCH.name,
                                        icon = rememberVectorPainter(image = Icons.Default.Search)
                                    ),
                                    BottomNavItem(
                                        NavigationItem.CreatePost.route,
                                        Screen.CREATE_POST.name,
                                        icon = rememberVectorPainter(image = Icons.Default.AddCircle)
                                    ),
                                    BottomNavItem(
                                        NavigationItem.Reels.route,
                                        Screen.TWEET_LIST.name,
                                        icon = rememberVectorPainter(Icons.Default.RepeatOn)
                                    ),
                                    BottomNavItem(
                                        NavigationItem.Profile.route,
                                        Screen.PROFILE.name,
                                        icon = rememberVectorPainter(image = Icons.Default.Person)
                                    ),
                                ),
                                navController = navController,
                                onItemClick = {
                                    if (it.route == NavigationItem.Profile.route) {
                                        navController.navigate(
                                            "${NavigationItem.Profile.route}/$MY_USER_ID"
                                        )
                                    } else {
                                        navController.navigate(it.route)
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                AppNavHost(
                    mainViewModel,
                    navController,
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}


expect fun getDeviceType(): Int

expect fun getPlatformName(): String
