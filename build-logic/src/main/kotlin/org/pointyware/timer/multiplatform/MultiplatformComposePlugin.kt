package org.pointyware.timer.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 */
class MultiplatformComposePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
        }
    }
}
