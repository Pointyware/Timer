enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        mavenLocal()

        maven {
            url = uri("https://maven.pkg.github.com/Pointyware/Kass")
            credentials {
                username = providers.gradleProperty("github.user").get()
                password = providers.gradleProperty("github.token").get()
            }
        }
    }
}
rootProject.name = "Timer"

include(
    ":core-entities",
    ":core-data",
    ":core-interactors",
)

include(
    ":app-shared",
    ":app-android",
    ":app-browser",
    ":app-desktop"
)

include(
    ":app-android:baselineprofile"
)

include(
    ":core-ui"
)
include(
    ":feature-calendar",
    ":feature-metrics",
    ":feature-organizer",
    ":feature-tasks"
)

includeBuild("build-logic")
