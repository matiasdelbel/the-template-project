plugins {
    alias(libs.plugins.android.hilt) apply false

    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false

    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}
