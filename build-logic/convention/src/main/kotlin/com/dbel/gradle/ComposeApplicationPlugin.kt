package com.dbel.gradle

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.ComposeOptions
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dbel.gradle.plugin.build
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

@Suppress(names = ["UnstableApiUsage"])
class ComposeApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project) = project.build {
        val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = versionCatalog
                .findVersion("compose")
                .get()
                .toString()
        }
    }

    private fun Project.buildFeatures(block: BuildFeatures.() -> Unit) = extensions
        .getByType<ApplicationExtension>()
        .apply { buildFeatures{ block() } }

    private fun Project.composeOptions(block: ComposeOptions.() -> Unit) {
        extensions
            .getByType<ApplicationExtension>()
            .apply { composeOptions { block() } }
    }
}
