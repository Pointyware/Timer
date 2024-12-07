
plugins {
    id("pointyware.timer.multiplatform.library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {

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
    namespace = "org.pointyware.timer.organizer"
}
