@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "com.app.container.contract"

    defaultConfig { vectorDrawables { useSupportLibrary = true } }

    buildTypes {
        release { isMinifyEnabled = false }
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
}
