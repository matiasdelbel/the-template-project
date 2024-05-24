package com.dbel.gradle

import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.ComposeOptions
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dbel.gradle.plugin.build
import com.dbel.gradle.plugin.composeVersion
import com.dbel.gradle.plugin.versionCatalog
import org.gradle.kotlin.dsl.getByType

/**
 * See https://developer.android.com/jetpack/androidx/releases/compose-kotlin for more information.
 */
@Suppress(names = ["UnstableApiUsage"])
class ComposeLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) = project.build {
        buildFeatures { compose = true }

        composeOptions { kotlinCompilerExtensionVersion = versionCatalog.composeVersion }
    }

    private fun Project.buildFeatures(block: BuildFeatures.() -> Unit) = extensions
        .getByType<LibraryExtension>()
        .apply { buildFeatures{ block() } }

    private fun Project.composeOptions(block: ComposeOptions.() -> Unit) {
        extensions
            .getByType<LibraryExtension>()
            .apply { composeOptions { block() } }
    }
}
