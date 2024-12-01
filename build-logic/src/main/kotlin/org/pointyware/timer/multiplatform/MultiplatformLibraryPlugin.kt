package org.pointyware.timer.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.pointyware.timer.configureKotlinAndroid
import org.pointyware.timer.configureKotlinJvm
import org.pointyware.timer.libs

/**
 *
 */
class MultiplatformLibraryPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {

            with(plugins) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }
            configureKotlinAndroid()
            configureKotlinJvm()

            dependencies {
                add("commonMainImplementation", project.libs.findLibrary("kotlinx.datetime"))
            }
        }
    }
}
