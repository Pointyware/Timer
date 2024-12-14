
plugins {
    id("org.pointyware.timer.multiplatform.library")
    id("org.pointyware.timer.multiplatform.compose")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.featureTasks)

                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(compose.components.resources)
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

compose.resources {
    publicResClass = true
    packageOfResClass = "org.pointyware.timer.organizer"
    generateResClass = auto
}
