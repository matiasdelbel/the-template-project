@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.application.compose)
    alias(libs.plugins.android.hilt)

    kotlin("kapt")
}

android {
    namespace = "com.template.playground"

    defaultConfig {
        applicationId = "com.template.playground"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.apps.trackingApp) // The application dependency loaded in the container
    
    implementation(projects.foundation.designSystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.activity)
    implementation(libs.compose.coil)
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
