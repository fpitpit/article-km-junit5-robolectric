package fr.pitdev.article.kmtest.utils

import androidx.compose.runtime.Composable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect open class UsingContext() {
    @Composable
    fun initPreviewContextConfiguration()
}