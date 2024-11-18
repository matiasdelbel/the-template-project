plugins {
    `kotlin-dsl`
}

group = "com.dbel.gradle.plugins"
sourceSets["main"].java.srcDirs("src/main/kotlin-extensions")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("com.android.tools.build:gradle:8.5.1")
    
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
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
