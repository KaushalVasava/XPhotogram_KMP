import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.kaushalvasava.apps.instagram.MainView
import moe.tlaster.precompose.PreComposeWindow

fun main() =
    application {
        val windowState = rememberWindowState()
       PreComposeWindow(
            onCloseRequest = {

            },
            state = windowState,
            title = "Instagram"
        ) {
            MainView()
        }
    }