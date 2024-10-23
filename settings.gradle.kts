@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

include(
    /* App container module */
    "app-container:playground",
    "app-container:showcase",

    /* Foundation modules */
    "foundation:data",
    "foundation:design-system",

    /* App modules */
    "apps:rijks-app",
    "apps:tmdb-app",
    "apps:tracking-app",
)
