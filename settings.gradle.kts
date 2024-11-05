enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
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
        mavenLocal()
    }
}
rootProject.name = "Timer"

include(":app-shared")
include(":app-android")
include(":app-browser")
//include(":app-ios")
include(":app-desktop")

include(":feature-calendar")
include(":feature-metrics")
include(":feature-organizer")
include(":feature-tasks")
