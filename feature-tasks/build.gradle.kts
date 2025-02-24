
plugins {
    id("org.pointyware.timer.multiplatform.library")
    id("org.pointyware.timer.multiplatform.compose")
    id("org.pointyware.timer.multiplatform.koin")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.resources)

                implementation(libs.compose.viewmodel)
                implementation(libs.compose.navigation)

                implementation(libs.koin.core)

                api(projects.coreInteractors)
                api(projects.coreUi)
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

compose.resources {
    publicResClass = true
    packageOfResClass = "org.pointyware.timer.tasks"
    generateResClass = auto
}
