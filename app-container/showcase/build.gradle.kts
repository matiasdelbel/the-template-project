plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.application.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.template.showcase"

    defaultConfig {
        applicationId = "com.template.showcase"

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // region Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.lifecycle)
    debugImplementation(libs.compose.ui.tooling)
    // endregion

    // region Hilt
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)
    // endregion

    // region Projects
    implementation(projects.apps.holidaysApp)
    implementation(projects.apps.rijksApp)
    implementation(projects.apps.tmdbApp)
    implementation(projects.apps.trackingApp)
    implementation(projects.foundation.designSystem)
    // endregion
}
