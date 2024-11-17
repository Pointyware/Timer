
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.sqlDelight)
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
                api(projects.coreEntities)
                api(projects.coreInteractors)
                api(projects.coreData)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.dateTime)

                implementation(compose.ui)
                implementation(compose.material3)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.koin.test)

                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutinesTest)
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
                implementation(libs.sqlDelight.jvm)
            }
        }

        val desktopTest by getting {
            dependsOn(jvmSharedTest)
        }

        val androidMain by getting {
            dependsOn(jvmSharedMain)

            dependencies {
                implementation(libs.sqlDelight.android)

                implementation(libs.koin.android)
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

sqldelight {
    databases {
        create("TimerDatabase") {
            packageName = "org.pointyware.timer.shared.db"
        }
    }
}
