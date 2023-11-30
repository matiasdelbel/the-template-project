package com.dbel.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.dbel.gradle.plugin.androidApplicationPluginId
import com.dbel.gradle.plugin.kotlinAndroidPluginId
import com.dbel.gradle.plugin.kotlinOptions
import com.dbel.gradle.plugin.libs
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

@Suppress(names = ["UnstableApiUsage"])
class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val catalog = target.extensions.getByType<VersionCatalogsExtension>().libs

        with(target) {
            with(pluginManager) {
                apply(catalog.androidApplicationPluginId)
                apply(catalog.kotlinAndroidPluginId)
            }

            extensions.configure<BaseAppModuleExtension> {
                buildToolsVersion = "34.0.0"
                compileSdk = 34

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