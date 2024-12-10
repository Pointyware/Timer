
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

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.components.resources)
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

compose.resources {
    publicResClass = true
    packageOfResClass = "org.pointyware.timer.calendar"
    generateResClass = always
}
