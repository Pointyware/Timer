plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("org.pointyware.timer.multiplatform.compose")
    id("org.pointyware.timer.multiplatform.koin")
}

kotlin {
    jvmToolchain(21)

    jvm()

    sourceSets {
        val jvmMain by getting {

            dependencies {
                implementation(projects.appShared)

                implementation(compose.ui)
                implementation(compose.desktop.currentOs)
                implementation(compose.components.resources)
            }
        }

        val jvmTest by getting {

            dependencies {

            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.pointyware.timer.desktop.ApplicationKt"

//        nativeDistributions {
//            targetFormats(TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Dmg)
//            packageName = rootProject.group.toString()
//            packageVersion = rootProject.version.toString()
//        }
    }
}

compose.resources {
    generateResClass = always
    publicResClass = true
    packageOfResClass = "org.pointyware.timer.desktop"
}
