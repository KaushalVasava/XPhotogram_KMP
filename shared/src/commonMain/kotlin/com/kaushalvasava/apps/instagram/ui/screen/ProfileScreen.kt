@file:OptIn(ExperimentalFoundationApi::class)

package com.kaushalvasava.apps.instagram.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material.icons.filled.VideoLabel
import androidx.compose.material.icons.filled.VideoStable
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.apps.instagram.models.UserType
import com.kaushalvasava.apps.instagram.ui.components.ButtonSection
import com.kaushalvasava.apps.instagram.ui.components.CenterCircularProgressBar
import com.kaushalvasava.apps.instagram.ui.components.HighlightSection
import com.kaushalvasava.apps.instagram.ui.components.MiddlePart
import com.kaushalvasava.apps.instagram.ui.components.PostSection
import com.kaushalvasava.apps.instagram.ui.components.PostTabView
import com.kaushalvasava.apps.instagram.ui.components.ProfileDescription
import com.kaushalvasava.apps.instagram.ui.navigation.NavigationItem
import com.kaushalvasava.apps.instagram.util.AppConstants.MY_USER_ID
import com.kaushalvasava.apps.instagram.viewmodel.HomeViewModel
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    userId: String,
    homeViewModel: HomeViewModel,
    navController: Navigator,
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val userState by remember {
        mutableStateOf(homeViewModel.getUserById(userId))
    }
    val admin by remember {
        mutableStateOf(homeViewModel.getUserById(MY_USER_ID))
    }
    val posts by remember {
        mutableStateOf(
            homeViewModel.getPosts(userState?.postIds ?: emptyList())
        )
    }
    val stories by remember {
        mutableStateOf(
            homeViewModel.getStories(userState?.storyIds ?: emptyList())
        )
    }
    val userType = if (userId == MY_USER_ID) {
        UserType.ADMIN
    } else if (admin!!.followingIds.any {
            it == userId
        }) {
        UserType.FOLLOWING
    } else {
        UserType.FOLLOWER
    }
    val user = userState
    if (user != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(user.id, fontSize = 18.sp) },
                actions = {
                    Row {
                        IconButton(onClick = {
                            navController.navigate(NavigationItem.Notification.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "notification",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        IconButton(onClick = { /* no-op */ }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "menu",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(2.dp))
            MiddlePart(
                user, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                navController
            )
            Spacer(modifier = Modifier.height(2.dp))
            ProfileDescription(
                displayName = user.name,
                description = user.bio,
                url = user.links.firstOrNull(),
                followedBy = listOf("Kaushal, Jerry"),
                otherCount = 34,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ButtonSection(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                userType = userType
            )
            Spacer(modifier = Modifier.height(16.dp))
            HighlightSection(
                modifier = Modifier
                    .fillMaxWidth(),
                highlights = stories,
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
            PostTabView(
                imageWithText = listOf(
                    "Posts" to
                            Icons.Default.Photo,
                    "Reels" to Icons.Default.Videocam,
                    "Profile" to Icons.Default.Tag,
                )
            ) {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> PostSection(
                    posts = posts,
                    modifier = Modifier.fillMaxWidth(),
                    navController
                )
            }
        }
    } else {
        CenterCircularProgressBar()
    }
}