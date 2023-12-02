package com.kaushalvasava.apps.instagram

import androidx.compose.ui.window.ComposeUIViewController


actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { App() }

actual fun getDeviceType(): Int {
    return 0
}
