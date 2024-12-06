
plugins {
    id("pointyware.timer.multiplatform.library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.sqlDelight)
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.coreEntities)
                api(projects.coreInteractors)
                api(projects.coreData)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.dateTime)

                implementation(compose.ui)
                implementation(compose.material3)

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
    compileSdk = 35
}

sqldelight {
    databases {
        create("TimerDatabase") {
            packageName = "org.pointyware.timer.shared.db"
        }
    }
}
