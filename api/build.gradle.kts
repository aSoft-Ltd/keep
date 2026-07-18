plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "An API to help caching simple objects"

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xenable-suspend-function-exporting")
    }
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js { library() }
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.WASM) wasmWasi { library() }
    if (Targeting.OSX) osxTargets() else listOf()
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        commonMain.dependencies {
            api(kotlinx.serialization.core)
            api(libs.kotlinx.exports)
        }
    }
}