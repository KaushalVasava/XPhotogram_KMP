package com.kaushalvasava.apps.instagram.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaushalvasava.apps.instagram.models.Tweet
import com.kaushalvasava.apps.instagram.ui.components.CircularImage
import com.kaushalvasava.apps.instagram.util.DemoData
import com.kaushalvasava.apps.instagram.viewmodel.HomeViewModel
import kotlinx.datetime.TimeZone
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTweetScreen(homeViewModel: HomeViewModel, navController: Navigator) {
    val user = DemoData.demoUser
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                homeViewModel.updateTweet(
                    tweet = Tweet(description = text, userId = user.id)
                )
                navController.popBackStack()
            }) {
                Icon(Icons.Default.Done, contentDescription = "Add")
            }
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = { Text("Create Tweet", fontSize = 18.sp) },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription =
                        "back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                }
            )
            Row(
                Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularImage(imageUrl = user.profileImage, imageSize = 45.dp)
                Spacer(Modifier.width(8.dp))
                Text(
                    user.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("time",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            TextField(
                value = text,
                onValueChange = { q ->
                    text = q
                },
                placeholder = {
                    Text("Write your thoughts", color = Color.Gray)
                },
                modifier = Modifier.fillMaxSize(1f)

            )
        }
    }
}