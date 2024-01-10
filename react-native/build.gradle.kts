plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "An implementation of the cache-api to help caching simple objects on react-native-targets"

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val jsMain by getting {
            dependencies {
                api(projects.keepApi)
                api(kotlinx.serialization.json)
                api(npm("@react-native-async-storage/async-storage", npm.versions.asyncStorage.get()))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(projects.keepTest)
            }
        }
    }
}