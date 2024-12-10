package fr.pitdev.article.kmtest

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import fr.pitdev.article.kmtest.utils.UsingContext
import kotlin.test.Test

class AppTest: UsingContext() {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun myTest() = runComposeUiTest {
        setContent {
            MaterialTheme {
                App()
            }
        }
        onRoot().printToLog("AppTest")
        onNodeWithText("Click me!").performClick()
    }
}