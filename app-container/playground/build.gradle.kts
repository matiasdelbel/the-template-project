plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.application.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
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
    implementation(projects.apps.holidaysApp) // The module to lunch in the playground
    implementation(projects.foundation.designSystem)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.activity)
    implementation(libs.compose.viewmodel)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)
}
