import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "An implementation of the cache-api to help caching simple objects on the browser for js and wasmJs"

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

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = npm.versions.node.version.get()
    nodeDownloadBaseUrl = npm.versions.node.url.get()
}

rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}

tasks.named("wasmJsTestTestDevelopmentExecutableCompileSync").configure {
    mustRunAfter(tasks.named("jsBrowserTest"))
}