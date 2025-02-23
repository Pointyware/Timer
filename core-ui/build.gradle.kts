
plugins {
    id("org.pointyware.timer.multiplatform.library")
    id("org.pointyware.timer.multiplatform.compose")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.resources)

                implementation(libs.koin.core)

                api(libs.kotlinx.collections.immutable)

                api(projects.coreEntities)
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
    namespace = "org.pointyware.timer.ui"
}
