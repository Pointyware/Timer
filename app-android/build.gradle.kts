plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    id("org.pointyware.timer.multiplatform.compose")
    alias(libs.plugins.hilt)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "org.pointyware.timer"

    compileSdk = 35

    defaultConfig {
        minSdk = 21
        targetSdk = 35

        versionCode = 1
        versionName = libs.versions.app.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("META-INF/gradle/*")
        }
    }
}

dependencies {
    implementation(projects.appShared)

    implementation(compose.ui)
    implementation(compose.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.composeMaterial3)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.tooling.preview.v170)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose.v140)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.koin.android)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)

    testImplementation(libs.kotlinx.coroutinesTest)
    testImplementation(libs.junit)
    testImplementation(libs.kass.assertions)
    androidTestImplementation(libs.kass.assertions)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.junit4)
    debugImplementation(libs.androidx.composeTooling)
}
