package org.pointyware.timer.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

/**
 *
 */
class KotlinConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configure<KotlinProjectExtension> {
                jvmToolchain(21)
            }
        }
    }
}
