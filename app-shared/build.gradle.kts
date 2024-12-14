
plugins {
    id("org.pointyware.timer.multiplatform.library")
    id("org.pointyware.timer.multiplatform.compose")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.featureMetrics)
                api(projects.featureCalendar)
                api(projects.featureTasks)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.dateTime)

                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
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
            }
        }

        val desktopTest by getting {
        }

        val androidMain by getting {
            dependencies {
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
