
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
            id = "org.pointyware.timer.multiplatform.library"
            implementationClass = "org.pointyware.timer.multiplatform.MultiplatformLibraryPlugin"
        }
        register("multiplatformCompose") {
            id = "org.pointyware.timer.multiplatform.compose"
            implementationClass = "org.pointyware.timer.multiplatform.MultiplatformComposePlugin"
        }
        register("multiplatformKoin") {
            id = "org.pointyware.timer.multiplatform.koin"
            implementationClass = "org.pointyware.timer.multiplatform.MultiplatformKoinPlugin"
        }
        register("kotlinConvention") {
            id = "org.pointyware.timer.kotlin"
            implementationClass = "org.pointyware.timer.multiplatform.KotlinConventionPlugin"
        }
    }
}
