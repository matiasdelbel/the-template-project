package com.dbel.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.dbel.gradle.plugin.androidApplicationPluginId
import com.dbel.gradle.plugin.kotlinAndroidPluginId
import com.dbel.gradle.plugin.kotlinOptions
import com.dbel.gradle.plugin.versionCatalog
import org.gradle.api.JavaVersion

@Suppress(names = ["UnstableApiUsage"])
class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(versionCatalog.androidApplicationPluginId)
                apply(versionCatalog.kotlinAndroidPluginId)
            }

            extensions.configure<BaseAppModuleExtension> {
                buildToolsVersion = "35.0.0"
                compileSdk = 35

                defaultConfig { minSdk = 26 }

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
}