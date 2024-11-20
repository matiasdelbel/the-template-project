plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.library.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tracking.app"

    defaultConfig { vectorDrawables { useSupportLibrary = true } }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.foundation.designSystem)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.activity)
    implementation(libs.compose.viewmodel)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}

