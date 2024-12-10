
plugins {
    id("pointyware.timer.multiplatform.library")
    id("pointyware.timer.multiplatform.compose")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.featureOrganizer)

                implementation(compose.ui)
                implementation(compose.material3)
            }
        }

        val commonTest by getting {

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
    namespace = "org.pointyware.timer.metrics"
}
