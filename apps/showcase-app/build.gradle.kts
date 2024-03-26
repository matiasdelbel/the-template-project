plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.library.compose)
}

android {
    namespace = "com.showcase.app"

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
    implementation(projects.apps.contract)
    implementation(projects.apps.holidaysBudgetApp)
    implementation(projects.apps.rijksApp)
    implementation(projects.apps.trackingApp)
    implementation(projects.apps.tmdbApp)

    implementation(projects.foundation.designSystem)

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
}
