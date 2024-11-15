
plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    application
}

version = libs.versions.app.get()

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.appShared)
    implementation(projects.featureTasks)
    implementation(projects.featureOrganizer)
    implementation(projects.featureCalendar)
    implementation(projects.featureMetrics)

    implementation(compose.ui)
    implementation(compose.desktop.currentOs)
}

application {
    mainClass = "org.pointyware.timer.desktop.ApplicationKt"
}
