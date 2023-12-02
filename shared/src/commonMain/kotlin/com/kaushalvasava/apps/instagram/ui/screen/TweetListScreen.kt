package com.kaushalvasava.apps.instagram.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.apps.instagram.models.ApiFailure
import com.kaushalvasava.apps.instagram.models.BaseState
import com.kaushalvasava.apps.instagram.ui.components.CenterCircularProgressBar
import com.kaushalvasava.apps.instagram.ui.components.CenterErrorText
import com.kaushalvasava.apps.instagram.ui.components.TweetItem
import com.kaushalvasava.apps.instagram.ui.navigation.NavigationItem
import com.kaushalvasava.apps.instagram.viewmodel.HomeViewModel
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetListScreen(homeViewModel: HomeViewModel, navController: Navigator) {
    val tweetsState = homeViewModel.tweets.collectAsState()
    when (val state = tweetsState.value) {
        is BaseState.Failed -> {
            when (state.error) {
                is ApiFailure.Unknown -> {
                    CenterErrorText(msg = state.error.error)
                }
            }
        }

        BaseState.Loading -> {
            CenterCircularProgressBar()
        }

        is BaseState.Success -> {
            val tweets = state.data
            Column(Modifier.fillMaxSize()) {
                TopAppBar(
                    title = { Text("Tweets", fontSize = 18.sp) },
                    navigationIcon = {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                    }
                )
                LazyColumn(Modifier.fillMaxSize(1f)) {
                    items(tweets, ) {
                        TweetItem(tweet = it, onCommentClick = {
                            navController.navigate(NavigationItem.CreateTweet.route)
                        }) {
                            navController.navigate("${NavigationItem.Tweet.route}/${it.id}")
                        }
                    }
                }
            }
        }
    }
}