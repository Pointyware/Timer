
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvmToolchain(17)

    jvm("desktop")
    androidTarget()
    js {
        browser {
            binaries.executable()
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }

        val commonTest by getting {

        }

        val jvmSharedMain by creating {

        }

        val jvmSharedTest by creating {

        }

        val desktopMain by getting {
            dependsOn(jvmSharedMain)

            dependencies {

            }
        }

        val desktopTest by getting {

        }

        val androidMain by getting {
            dependsOn(jvmSharedMain)

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
    compileSdk = 35
}
