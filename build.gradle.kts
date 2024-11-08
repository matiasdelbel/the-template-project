plugins {
    alias(libs.plugins.android.hilt) apply false

    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false

    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.org.jetbrains.kotlin.android) apply false

    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20" apply false
}
