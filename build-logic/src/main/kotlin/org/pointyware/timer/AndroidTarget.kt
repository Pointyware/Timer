package org.pointyware.timer

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

/**
 * Configure base Kotlin with Android options
 */
fun Project.configureKotlinAndroid() {
    extensions.getByType(CommonExtension::class.java).also {
        configureKotlinAndroid(it)
    }
}

fun configureKotlinAndroid(extension: CommonExtension<*, *, *, *, *, *>) {
    with(extension) {
        compileSdk = 35

        defaultConfig {
            minSdk = 21
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        packaging {
            resources {
                excludes.add("META-INF/gradle/*")
            }
        }
    }
}
