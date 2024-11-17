
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
                implementation(projects.coreEntities)
            }
        }

        val commonTest by getting {

        }

        val jvmSharedMain by creating {
            dependsOn(commonMain)
        }

        val jvmSharedTest by creating {
            dependsOn(commonTest)
        }

        val desktopMain by getting {
            dependsOn(jvmSharedMain)

            dependencies {

            }
        }

        val desktopTest by getting {
            dependsOn(jvmSharedTest)
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
    namespace = "org.pointyware.timer.data"
    compileSdk = 35
}
