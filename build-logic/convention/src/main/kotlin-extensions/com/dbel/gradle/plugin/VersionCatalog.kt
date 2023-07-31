package com.dbel.gradle.plugin

import org.gradle.api.artifacts.VersionCatalog

val VersionCatalog.androidApplicationPluginId: String
    get() = findPlugin("com-android-application").get().get().pluginId

val VersionCatalog.androidLibraryPluginId: String
    get() = findPlugin("com-android-library").get().get().pluginId

val VersionCatalog.kotlinAndroidPluginId: String
    get() = findPlugin("org-jetbrains-kotlin-android").get().get().pluginId
