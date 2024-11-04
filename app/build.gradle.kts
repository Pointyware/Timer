plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
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
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
        }
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(compose.ui)
    implementation(compose.material)
//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation "androidx.compose.material:material:$compose_version"
//    implementation "androidx.compose.material:material-icons-core:$compose_version"
//    implementation "androidx.compose.material:material-icons-extended:$compose_version"
//    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.4.1'
//    implementation 'androidx.activity:activity-compose:1.4.0'
//    implementation "androidx.navigation:navigation-compose:$navigation_compose_version"
//
//    // region Room
//
//    implementation "androidx.room:room-runtime:$room_version"
//    annotationProcessor "androidx.room:room-compiler:$room_version"
//    ksp "androidx.room:room-compiler:$room_version"
//    implementation "androidx.room:room-ktx:$room_version"
//    testImplementation "androidx.room:room-testing:$room_version"
//
//    // endregion
//
//    testImplementation "junit:junit:$junit_version"
//
//    androidTestImplementation "junit:junit:$junit_version"
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    debugImplementation(libs.androidx.composeTooling)
}
