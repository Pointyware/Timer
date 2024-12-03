
plugins {
    id("pointyware.timer.multiplatform.library")
}

kotlin {
    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.coreEntities)
            }
        }
    }
}

android {
    namespace = "org.pointyware.timer.data"
    compileSdk = 35
}
