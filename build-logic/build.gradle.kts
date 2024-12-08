
plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("multiplatformLibrary") {
            id = "pointyware.timer.multiplatform.library"
            implementationClass = "org.pointyware.timer.multiplatform.MultiplatformLibraryPlugin"
        }
        register("multiplatformCompose") {
            id = "pointyware.timer.multiplatform.compose"
            implementationClass = "org.pointyware.timer.multiplatform.MultiplatformComposePlugin"
        }
    }
}
