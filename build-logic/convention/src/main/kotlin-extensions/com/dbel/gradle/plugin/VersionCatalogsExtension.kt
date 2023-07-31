package com.dbel.gradle.plugin

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

val VersionCatalogsExtension.libs: VersionCatalog get() = find("libs").get()
