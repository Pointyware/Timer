import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    id("org.pointyware.timer.multiplatform.compose")
    id("org.pointyware.timer.multiplatform.koin")
    application
}

version = libs.versions.app.get()

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.appShared)

    implementation(compose.ui)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "org.pointyware.timer.desktop.ApplicationKt"

        nativeDistributions {
            targetFormats(TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Dmg)
            packageName = group.toString()
            packageVersion = version.toString()
        }
    }
}
