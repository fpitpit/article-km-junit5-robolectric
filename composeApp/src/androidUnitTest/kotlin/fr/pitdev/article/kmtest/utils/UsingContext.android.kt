package fr.pitdev.article.kmtest.utils

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.ibm.icu.impl.Assert
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.TestLifecycleApplication
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplicationPackageManager
import org.robolectric.shadows.ShadowLog
import tech.apter.junit.jupiter.robolectric.RobolectricExtension
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExtendWith(RobolectricExtension::class)
@Config(
    sdk = [Config.OLDEST_SDK, Build.VERSION_CODES.UPSIDE_DOWN_CAKE, Build.VERSION_CODES.VANILLA_ICE_CREAM])
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class UsingContext {

    @BeforeTest
    open fun setUp() {
        ShadowLog.stream = System.out
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    actual fun initPreviewContextConfiguration() {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            PreviewContextConfigurationEffect()
        }
    }
}