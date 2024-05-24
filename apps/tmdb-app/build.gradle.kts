@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.library.compose)
    alias(libs.plugins.android.hilt)

    kotlin("kapt")
}

android {
    namespace = "com.tmdb.app"

    defaultConfig {
        vectorDrawables { useSupportLibrary = true }

        val localPrperties = Properties().apply { load(FileInputStream(File(rootProject.rootDir, "local.properties"))) }
        val tmdbApiKey: String = localPrperties.getProperty("tmdb.api.key")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.activity)
    implementation(libs.compose.coil)
    implementation(libs.compose.material)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.paging)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.viewmodel)

    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.logging)
    implementation(libs.retrofit.moshi)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    testImplementation(libs.test.androidx.core)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.test.junit)
    testImplementation (libs.test.mockito)
}

kapt {
    correctErrorTypes = true
}
