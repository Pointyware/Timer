import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("org.pointyware.timer.multiplatform.compose")
    publishing
    signing
}

kotlin {
    jvmToolchain(17)

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "webApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.rootDir.path)
                        add(project.projectDir.path)
                    }
//                    port = 8080
                }
            }
        }
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
//                implementation(projects.appShared)
            }
        }

        val commonTest by getting {

        }

//        val wasmMain by getting {
//
//        }
//
//        val wasmTest by getting {
//
//        }

//        val jsMain by getting {
//
//        }
//
//        val jsTest by getting {
//
//        }
    }
}

signing {

}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
    repositories {
        maven("GitHub") {
            url = uri("https://maven.pkg.github.com/pointyware/timer")
            credentials {
                username = providers.gradleProperty("github.user").getOrElse("no-user")
                password = providers.gradleProperty("github.token").getOrElse("no-pass")
            }
        }
    }
}

tasks.create<Task>("list-components") {
    description = "List all components"

    doLast {
        println("Components:")
        project.components.forEach {
            println(it.name)
        }
    }
}
