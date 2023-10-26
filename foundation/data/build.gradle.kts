@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)

    kotlin("kapt")
}

android {
    namespace = "com.common.data"

    defaultConfig {
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.compose.paging)

    implementation(libs.retrofit)
    implementation(libs.retrofit.logging)
    implementation(libs.retrofit.moshi)
}

kapt {
    correctErrorTypes = true
}
