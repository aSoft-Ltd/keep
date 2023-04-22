plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "An implementation of the cache-api to help caching simple objects in memory"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.koncurrentExecutorsMock)
                api(projects.keepApi)
                api(projects.keepFile)
                api(projects.kollectionsAtomic)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.keepTest)
            }
        }
    }
}