
plugins {
    alias(libs.plugins.kotlinJvm)
    id("pointyware.timer.multiplatform.compose")
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

application {
    mainClass = "org.pointyware.timer.desktop.ApplicationKt"
}
