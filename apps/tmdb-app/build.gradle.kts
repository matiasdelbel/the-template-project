plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.library.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.tmdb.app"

    defaultConfig {
        vectorDrawables { useSupportLibrary = true }

        val tmdbApiKey = System.getenv("API_KEY_TMDB") ?: properties["API_KEY_TMDB"] as String
        resValue("string", "tmdb_api_key", tmdbApiKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.foundation.data)
    implementation(projects.foundation.designSystem)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.activity)
    implementation(libs.compose.viewmodel)
    implementation(libs.runtime.android)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.bundles.ktor)
}
