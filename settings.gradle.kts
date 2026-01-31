rootProject.name = "Miyo"
include(":app", ":core", ":data", ":extensions")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    plugins {
        id("com.android.application") version "8.3.3"
        id("org.jetbrains.kotlin.android") version "1.9.22"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
