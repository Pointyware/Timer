
plugins {
    `kotlin-dsl`
}

dependencies {

}

gradlePlugin {
    plugins {
        register("multiplatformLibrary") {
            id = "pointyware.timer.multiplatform.library"
            implementationClass = "org.pointyware.timer.multiplatform.MultiplatformLibraryPlugin"
        }
    }
}
