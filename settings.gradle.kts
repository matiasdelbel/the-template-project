@file:Suppress("UnstableApiUsage")

// TODO Rename the root project name
rootProject.name = "template-android"

pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()

        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

/* Modules in the project */
include(":app")
// TODO Rename the module name
include(":lib")
