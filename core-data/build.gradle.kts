import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("pointyware.timer.multiplatform.library")
    alias(libs.plugins.sqlDelight)
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs = listOf("-Xexpect-actual-classes")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.coreEntities)

                implementation(libs.koin.core)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.sqlDelight.jvm)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)

                implementation(libs.koin.android)
            }
        }
    }
}

android {
    namespace = "org.pointyware.timer.data"
}

sqldelight {
    databases {
        create("TimerDatabase") {
            packageName = "org.pointyware.timer.data.db"
        }
    }
}
