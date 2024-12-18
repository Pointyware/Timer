
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("org.pointyware.timer.multiplatform.compose")
}

version = libs.versions.app.get()

kotlin {
    jvmToolchain(17)

//    wasmJs {
//        browser {
//            binaries.executable()
//        }
//    }
    js {
        browser {
            binaries.executable()
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
//                implementation(projects.appShared)
            }
        }

        val commonTest by getting {

        }

//        val wasmMain by getting {
//
//        }
//
//        val wasmTest by getting {
//
//        }

//        val jsMain by getting {
//
//        }
//
//        val jsTest by getting {
//
//        }
    }
}
