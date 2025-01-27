package org.pointyware.timer.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.pointyware.timer.libs

/**
 *
 */
class MultiplatformKoinPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            when {
                pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform") -> {
                    configure<KotlinMultiplatformExtension> {
                        sourceSets.apply {
                            commonMain.dependencies {
                                implementation(libs.findLibrary("koin-core").get())
                            }
                            if (pluginManager.hasPlugin("org.jetbrains.kotlin.android")) {
                                androidMain.dependencies {
                                    implementation(libs.findLibrary("koin-android").get())
                                }
                            }
                        }
                    }
                }

                pluginManager.hasPlugin("org.jetbrains.kotlin.jvm") -> {
                    dependencies {
                        add("implementation", libs.findLibrary("koin-core").get())
                    }
                }

                pluginManager.hasPlugin("org.jetbrains.kotlin.android") -> {
                    dependencies {
                        add("implementation", libs.findLibrary("koin-android").get())
                    }
                }
            }
        }
    }
}
