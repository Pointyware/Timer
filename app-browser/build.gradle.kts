
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)

    wasmJs {
        browser {
            binaries.executable()
        }
    }
    js {
        browser {
            binaries.executable()
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.appShared)
                implementation(projects.featureTasks)
                implementation(projects.featureOrganizer)
                implementation(projects.featureCalendar)
                implementation(projects.featureMetrics)
            }
        }

        val commonTest by getting {

        }

        val wasmMain by getting {

        }

        val wasmTest by getting {

        }

        val jsMain by getting {

        }

        val jsTest by getting {

        }
    }
}
