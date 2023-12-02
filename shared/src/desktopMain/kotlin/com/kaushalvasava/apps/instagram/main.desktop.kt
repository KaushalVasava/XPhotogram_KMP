package com.kaushalvasava.apps.instagram

import androidx.compose.runtime.Composable

@Composable
fun MainView() = App()

actual fun getPlatformName(): String = "Desktop"

actual fun getDeviceType(): Int {
    return 2
}