package org.pointyware.timer.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.pointyware.timer.libs

/**
 *
 */
class MultiplatformLibraryPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project.plugins) {
            apply("org.jetbrains.kotlin.multiplatform")
            apply("com.android.library")
        }

        project.dependencies {
            add("commonMainImplementation", project.libs.findLibrary("kotlinx.datetime"))
        }
    }
}
