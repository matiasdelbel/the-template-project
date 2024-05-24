package com.dbel.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dbel.gradle.plugin.android
import com.dbel.gradle.plugin.androidLibraryPluginId
import com.dbel.gradle.plugin.build
import com.dbel.gradle.plugin.versionCatalog
import com.dbel.gradle.plugin.kotlinAndroidPluginId
import com.dbel.gradle.plugin.kotlinOptions
import com.dbel.gradle.plugin.plugins
import org.gradle.api.JavaVersion

@Suppress(names = ["UnstableApiUsage"])
class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) = target.build {
        plugins {
            apply(versionCatalog.androidLibraryPluginId)
            apply(versionCatalog.kotlinAndroidPluginId)
        }

        android {
            compileSdk = 34
            buildToolsVersion = "34.0.0"

            defaultConfig {
                targetSdk = 34
                minSdk = 26
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }
}
