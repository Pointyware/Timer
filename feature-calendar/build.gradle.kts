
plugins {
    id("pointyware.timer.multiplatform.library")
    id("pointyware.timer.multiplatform.compose")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.featureMetrics)

                implementation(libs.kotlinx.coroutines)
            }
        }

        val commonTest by getting {

        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutinesCoreJvm)
            }
        }

        val desktopTest by getting {
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutinesAndroid)
            }
        }

        val androidUnitTest by getting {

        }
        val androidInstrumentedTest by getting {

        }
    }
}

android {
    namespace = "org.pointyware.timer.calendar"
}
