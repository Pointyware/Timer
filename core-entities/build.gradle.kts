
plugins {
    id("pointyware.timer.multiplatform.library")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.dateTime)
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
    namespace = "org.pointyware.timer.entities"
}
