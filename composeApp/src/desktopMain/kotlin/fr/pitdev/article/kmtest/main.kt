package fr.pitdev.article.kmtest

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KM-UnitTest-Robolectric",
    ) {
        App()
    }
}