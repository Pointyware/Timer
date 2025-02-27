
plugins {
    id("org.pointyware.timer.multiplatform.library")
    id("org.pointyware.timer.multiplatform.compose")
    id("org.pointyware.timer.multiplatform.koin")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.featureMetrics)
                api(projects.featureCalendar)
                api(projects.featureTasks)

                implementation(libs.kotlinx.dateTime)

                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                implementation(compose.material3)
                implementation(compose.material3AdaptiveNavigationSuite)
                implementation(compose.components.resources)

                implementation(libs.compose.viewmodel)
                implementation(libs.compose.navigation)
            }
        }

        val commonTest by getting {
            dependencies {
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

compose.resources {
    publicResClass = true
    packageOfResClass = "org.pointyware.timer.shared"
    generateResClass = auto
}
