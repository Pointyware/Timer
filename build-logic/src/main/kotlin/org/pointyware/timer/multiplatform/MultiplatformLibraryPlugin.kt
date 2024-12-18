package org.pointyware.timer.multiplatform

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.pointyware.timer.configureKotlinAndroid
import org.pointyware.timer.configureKotlinJvm

/**
 *
 */
class MultiplatformLibraryPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "org.jetbrains.kotlin.multiplatform")
            apply(plugin = "com.android.library")
            apply(plugin = "org.pointyware.timer.kotlin")

            configureKotlinAndroid()
            configureKotlinJvm()

            extensions.configure(KotlinMultiplatformExtension::class.java) {
                jvm("desktop")
                androidTarget()

                applyDefaultHierarchyTemplate()

                with(sourceSets) {
                    commonMain.configure {
                        dependencies {

                        }
                    }

                    val jvmSharedMain = create("jvmSharedMain") {
                        dependsOn(commonMain.get())
                    }

                    val jvmSharedTest = create("jvmSharedTest") {
                        dependsOn(commonTest.get())
                    }

                    with(get("desktopMain")) {
                        dependsOn(jvmSharedMain)
//                        dependencies {
//                        }
                    }
                    with(get("desktopTest")) {
                        dependsOn(jvmSharedTest)
//                        dependencies {
//                        }
                    }

                    with(androidMain.get()) {
                        dependsOn(jvmSharedMain)
                        dependencies {

                        }
                    }
                }
            }
        }
    }
}
