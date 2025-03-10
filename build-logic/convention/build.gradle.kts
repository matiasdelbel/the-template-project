plugins {
    `kotlin-dsl`
    kotlin("jvm") version "2.1.10"
}

sourceSets["main"].java.srcDirs("src/main/kotlin-extensions")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.kotlin.stdlib)
}

gradlePlugin {
    plugins {
        /* Set up the basic configuration for an android app. */
        register("com.dbel.android.application") {
            id = "com.dbel.android.application"
            implementationClass = "com.dbel.gradle.AndroidApplicationPlugin"
        }

        /* Set up the basic configuration for an android module. */
        register("com.dbel.android.library") {
            id = "com.dbel.android.library"
            implementationClass = "com.dbel.gradle.AndroidLibraryPlugin"
        }

        /* Set up the basic configuration for an android compose module. */
        register("com.dbel.android.library.compose") {
            id = "com.dbel.android.library.compose"
            implementationClass = "com.dbel.gradle.ComposeLibraryPlugin"
        }

        /* Set up the basic configuration for an android compose app. */
        register("com.dbel.android.application.compose") {
            id = "com.dbel.android.application.compose"
            implementationClass = "com.dbel.gradle.ComposeApplicationPlugin"
        }
    }
}
