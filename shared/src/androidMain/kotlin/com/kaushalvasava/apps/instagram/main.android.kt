package com.kaushalvasava.apps.instagram

import androidx.compose.runtime.Composable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView() = App()

actual fun getDeviceType(): Int {
    return when{
        else -> 0
    }
}