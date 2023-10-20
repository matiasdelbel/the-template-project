@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

/* Foundation modules */
include("foundation:design-system")

/* Templates ready to use */
include("templates:empty-app")

/* Example apps */
include("examples:permissions")

// TODO Rename the module name - This should be inside feature:lib
include(":lib")
