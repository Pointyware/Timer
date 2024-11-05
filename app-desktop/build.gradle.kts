
plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.appShared)
    implementation(projects.featureTasks)
    implementation(projects.featureOrganizer)
    implementation(projects.featureCalendar)
    implementation(projects.featureMetrics)
}
