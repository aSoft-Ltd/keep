plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "An implementation of the cache-api to help caching simple objects on the browser"

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.keepApi)
                api(kotlinx.serialization.json)
            }
        }

        val test by getting {
            dependencies {
                implementation(projects.keepTest)
            }
        }
    }
}
