plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "An implementation of the cache-api to help caching simple objects in files"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasmJs { library() }
//    if (Targeting.WASM) wasmWasi { library() }
//    val osxTargets = if (Targeting.OSX) osxTargets() else listOf() // we have failing test on iosX64
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.keepApi)
                api(kotlinx.serialization.json)
                api(squareup.okio.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.keepTest)
                implementation(squareup.okio.fake)
                implementation(kotlinx.datetime) // was added because squareup.okio.fake kept bringing in an old dependency of kotlinx-datetime which failed the build
            }
        }
    }
}