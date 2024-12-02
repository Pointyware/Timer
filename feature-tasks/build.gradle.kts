
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)

    jvm("desktop")
    androidTarget()
//    js {
//        browser {
//            binaries.executable()
//        }
//    }

    applyDefaultHierarchyTemplate()

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
    namespace = "org.pointyware.timer.tasks"
    compileSdk = 35
}
