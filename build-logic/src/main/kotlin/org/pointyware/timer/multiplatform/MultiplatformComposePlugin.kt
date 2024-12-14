package org.pointyware.timer.multiplatform

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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

                    }
                }

                pluginManager.hasPlugin("org.jetbrains.kotlin.jvm") -> {
                    configure<JavaPluginExtension> {

                    }
                }

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
