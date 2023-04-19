plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    if (Targeting.JVM) jvm { library() }

    if (Targeting.JS) js(IR) { library() }

//    if (Targeting.WASM) wasm { library() }

    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    val nativeTargets = osxTargets + /*ndkTargets + mingwTargets */ linuxTargets

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.koncurrentLaterCore)
                api(kotlinx.serialization.core)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "An API to help caching simple objects"
)