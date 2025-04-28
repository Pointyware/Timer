plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    id("org.pointyware.timer.multiplatform.compose")
    alias(libs.plugins.hilt)
    alias(libs.plugins.androidBaseline)
}

kotlin {
    jvmToolchain(21)
}

parent?.let {
    group = it.group.toString() + ".android"
    version = it.version
}

android {
    namespace = group.toString()

    compileSdk = 35

    defaultConfig {
        minSdk = 21
        targetSdk = 35

        versionCode = 1
        versionName = libs.versions.app.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = providers.gradleProperty("keystore.password").getOrElse("no-pass")
            keyAlias = providers.gradleProperty("keystore.alias").getOrElse("no-alias")
            keyPassword = providers.gradleProperty("keystore.alias_password").getOrElse("no-pass")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            enableUnitTestCoverage = true
        }
        release {
            isMinifyEnabled = true
            proguardFiles.addAll(listOf(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            ))
            signingConfig = signingConfigs.getByName("release")
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
    implementation(libs.androidx.composePreview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose.v140)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.koin.android)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(libs.kotlinx.coroutinesTest)
    testImplementation(libs.junit)
//    testImplementation(libs.kass.assertions)
//    androidTestImplementation(libs.kass.assertions)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.junit4)

    debugImplementation(libs.androidx.composeTooling)

    implementation(libs.androidx.profileinstaller)
    baselineProfile(project(":app-android:baselineprofile"))
}
