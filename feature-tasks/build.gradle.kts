
plugins {
    id("pointyware.timer.multiplatform.library")
    id("pointyware.timer.multiplatform.compose")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kass.assertions)
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
    namespace = "org.pointyware.timer.tasks"
}
