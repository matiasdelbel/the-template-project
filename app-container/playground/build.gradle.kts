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
}

dependencies {
    // region Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.activity)
    implementation(libs.compose.viewmodel)
    debugImplementation(libs.compose.ui.tooling)
    // endregion

    // region Hilt
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)
    // endregion

    // region Local
    implementation(projects.apps.holidaysApp)
    implementation(projects.apps.rijksApp)
    implementation(projects.apps.tmdbApp)
    implementation(projects.apps.trackingApp)
    implementation(projects.foundation.designSystem)
    // endregion
}
