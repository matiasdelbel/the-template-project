package com.dbel.gradle.plugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().find("libs").get()

internal fun Project.build(block: Project.() -> Unit) = block()

internal fun Project.plugins(block: PluginManager.() -> Unit) = pluginManager.apply(block)

internal fun Project.android(block: LibraryExtension.() -> Unit) = extensions.configure<LibraryExtension> { block() }