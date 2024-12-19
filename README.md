![header](./images/header.png)
## Testing a Kotlin Multiplatform App with Compose and Robolectric

This guide explains how to set up unit tests for a Kotlin Multiplatform application using Compose and Robolectric.

 [Testing a Kotlin Multiplatform App with Compose and Robolectric](https://medium.com/publicis-sapient-france/testing-a-kotlin-multiplatform-app-with-compose-and-robolectric-45df4bef20e0)

**Why this is important:**

*   **Shared code:** Kotlin Multiplatform lets you share code between Android, iOS, Desktop, and Web.
*   **Efficient testing:** Robolectric enables fast unit tests by simulating the Android environment on your development machine.

**What you'll learn:**

*   How to configure a Kotlin Multiplatform project for testing.
*   How to write instrumented tests that run on a real Android device or emulator.
*   How to write unit tests using Robolectric and JUnit 5.
*   How to increase code coverage with Kover.

**Steps:**

1.  **Project Setup:**

    *   Create a `commonTest` directory in your `composeApp` module.
    *   Add the following dependencies to your `build.gradle.kts`:

        ```kotlin
        kotlin {
            sourceSets {
                commonTest.dependencies {
                    implementation(kotlin("test"))
                    implementation(compose.uiTest)
                }
                androidInstrumentedTest.dependencies {
                    implementation(compose.uiTest)
                }
            }
            androidTarget {
                instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
                dependencies {
                    debugImplementation(libs.androidx.ui.test.manifest)
                }
            }
        }
        android {
            defaultConfig {
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
        }
        ```

2.  **Write a Simple Test:**

    *   Create a test file (e.g., `AppTest.kt`) in `commonTest`.
    *   Write a test using `runComposeUiTest` to interact with a Compose component:

        ```kotlin
        class AppTest: UsingContext() {
            @Test
            fun myTest() = runComposeUiTest {
                setContent {
                    MaterialTheme {
                        App()
                    }
                }
                onNodeWithText("Click me!").performClick()
            }
        }
        ```

3.  **Run Instrumented Test:**

    *   Launch an Android emulator.
    *   Run the test: `./gradlew composeApp:connectedAndroidTest`

4.  **Add Robolectric and JUnit 5:**

    *   Upgrade to Kotlin 2.1.0.
    *   Add the Robolectric plugin and dependencies.
    *   Apply the plugin in your `build.gradle.kts`.

5.  **Create and Implement `UsingContext`:**

    *   Create an `expect` class named `UsingContext` in `commonTest`.
    *   Implement the `actual` class for Android in `androidTest`, configuring Robolectric.
    *   Implement empty `actual` classes for other platforms (iOS, Desktop, Web).

6.  **Add an Activity to the Manifest:**

    *   Add an empty `ComponentActivity` to your `AndroidManifest.xml`.

7.  **Run Robolectric Tests:**

    *   Run: `./gradlew composeApp:testDebugUnitTest`

8.  **Exclude `UsingContext` from `check` Task:**

    *   Prevent JUnit5 from misinterpreting `UsingContext` as a test class:

        ```kotlin
        tasks.withType<Test>().configureEach {
            exclude("fr/pitdev/article/kmtest/utils/UsingContext.class")
        }
        ```

    *   Run the check task: `./gradlew check`

**Bonus: Code Coverage with Kover**

1.  Add Kover dependencies and plugin.
2.  Apply the plugin and configure Kover in your `build.gradle.kts`.
3.  Generate an HTML report: `gradlew koverHtmlReport`

**Learn More:**

*   [Kover Repository](https://www.google.com/url?sa=E&source=gmail&q=https://github.com/Kotlin/kotlinx-kover)
*   [JUnit5 Robolectric Extension (Experimental)](https://github.com/apter-tech/junit5-robolectric-extension)

