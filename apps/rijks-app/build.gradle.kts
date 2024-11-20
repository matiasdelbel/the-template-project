plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.library.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rijks.app"

    defaultConfig {
        vectorDrawables { useSupportLibrary = true }

        val rijksApiKey = System.getenv("API_KEY_RIJKS") ?: properties["API_KEY_RIJKS"] as String
        resValue("string", "rijks_api_key", rijksApiKey)
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
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.bundles.ktor)
}
