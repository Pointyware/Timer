package org.pointyware.timer.multiplatform

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.pointyware.timer.configureKotlinJvm

/**
 *
 */
class MultiplatformComposePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.compose")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            when {
                pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform") -> {
                    configure<KotlinMultiplatformExtension> {

                        with(sourceSets) {
                            commonMain.dependencies {
//                                implementation(libs.findLibrary("koin-core").get())
                                // TODO: compose common dependencies
                            }
                            if (pluginManager.hasPlugin("org.jetbrains.kotlin.android")) {
                                androidMain.dependencies {
//                                    implementation(libs.findLibrary("koin-android").get())
                                    // TODO: compose android dependencies
                                }
                            }
                        }
                    }
                }

                pluginManager.hasPlugin("org.jetbrains.kotlin.jvm") -> {
                    configure<JavaPluginExtension> {
                        configureKotlinJvm(this)
                    }
                }

                pluginManager.hasPlugin("org.jetbrains.kotlin.android") -> {
                    when {
                        pluginManager.hasPlugin("com.android.library") -> {
                            configure<LibraryExtension> {

                            }
                        }

                        pluginManager.hasPlugin("com.android.application") -> {
                            configure<ApplicationExtension> {

                            }
                        }
                    }
                }
            }
        }
    }
}
