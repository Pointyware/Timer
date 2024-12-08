import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("pointyware.timer.multiplatform.library")
    id("pointyware.timer.multiplatform.compose")
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
                api(projects.coreInteractors)
                api(projects.coreData)
                api(projects.coreUi)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.dateTime)

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.material3AdaptiveNavigationSuite)

                implementation(libs.compose.viewmodel)
                implementation(libs.compose.navigation)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.koin.test)

                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutinesTest)
            }
        }


        val desktopMain by getting {
            dependencies {
                implementation(libs.sqlDelight.jvm)
            }
        }

        val desktopTest by getting {
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)

                implementation(libs.koin.android)
            }
        }

        val androidUnitTest by getting {

        }
        val androidInstrumentedTest by getting {

        }
    }
}

android {
    namespace = "org.pointyware.timer.shared"
}

sqldelight {
    databases {
        create("TimerDatabase") {
            packageName = "org.pointyware.timer.shared.db"
        }
    }
}
