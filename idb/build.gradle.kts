plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "An implementation of the cache-api to help caching simple objects on the browser using IndexedDB"

kotlin {
    if (Targeting.JS) js(IR) { browserLib() }
    if (Targeting.WASM) wasmJs { browserLib() }

    sourceSets {
        commonMain.dependencies {
            api(projects.keepApi)
            api(kotlinx.serialization.json)
        }

        commonTest.dependencies {
            implementation(projects.keepTest)
        }
    }
}

