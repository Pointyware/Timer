
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.androidBaseline) apply false
    alias(libs.plugins.androidTest) apply false
}

group = "org.pointyware.timer"
version = libs.versions.app.get()
