package com.kaushalvasava.apps.instagram.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.apps.instagram.models.User
import com.kaushalvasava.apps.instagram.ui.components.ActionButton
import com.kaushalvasava.apps.instagram.ui.components.CircularImage
import com.kaushalvasava.apps.instagram.ui.navigation.NavigationItem
import com.kaushalvasava.apps.instagram.viewmodel.HomeViewModel
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFollowListScreen(
    homeViewModel: HomeViewModel,
    navController: Navigator,
    isFollowing: Boolean,
    userId: String,
) {
    val userState by remember {
        mutableStateOf(homeViewModel.getUserById(userId))
    }
    val user = userState
    if (user != null) {
        val users = if (isFollowing) {
            homeViewModel.getFollowings(user)
        } else {
            homeViewModel.getFollowers(user)
        }
        Column {
            TopAppBar(
                title = {
                    Text(if (isFollowing) "Following" else "Followers", fontSize = 18.sp)
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                }
            )
            LazyColumn(Modifier.padding(horizontal = 16.dp)) {
                items(users.filterNot {
                    it.id == userId
                }) {
                    FollowItem(user = it,
                        isFollowing = isFollowing || (it.followingIds.any { id ->
                            id == userId
                        } && user.followingIds.any { id ->
                            id == it.id
                        }),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .clickable {
                                navController.navigate(
                                    "${NavigationItem.Profile.route}/${it.id}"
                                )
                            }
                    )
                }
            }
        }
    } else {
        Text("No Data")
    }
}

@Composable
fun FollowItem(user: User, modifier: Modifier, isFollowing: Boolean) {
    var isFollowed by remember {
        mutableStateOf(isFollowing)
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularImage(imageUrl = user.profileImage, imageSize = 50.dp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = user.name,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
        ActionButton(
            text = if (isFollowed) "Following" else "Follow",
            textColor = if (isFollowed) MaterialTheme.colorScheme.onBackground else Color.White,
            backgroundColor =
            if (isFollowed)
                MaterialTheme.colorScheme.inverseOnSurface
            else
                Color.Blue.copy(alpha = 0.7f),
            modifier = Modifier.clickable {
                isFollowed = !isFollowed
            }
        )
    }
}