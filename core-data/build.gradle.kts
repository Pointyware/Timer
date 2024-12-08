
plugins {
    id("pointyware.timer.multiplatform.library")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.coreEntities)
            }
        }
    }
}

android {
    namespace = "org.pointyware.timer.data"
}
