plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.library.compose)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.dbel.design.system"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)
}
