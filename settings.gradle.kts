pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf("koncurrent", "kommander").forEach {
    includeBuild("../$it")
}

rootProject.name = "keep"

includeSubs("keep", ".", "api", "browser", "file", "mock", "react-native", "test", "idb")
