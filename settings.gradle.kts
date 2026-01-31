rootProject.name = "Miyo"
include(":app", ":core", ":data", ":extensions")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://jitpack.io")
    }
    plugins {
        id("com.android.application") version "8.3.0"
        id("com.android.library") version "8.3.0"
        id("org.jetbrains.kotlin.android") version "1.9.25"
        id("org.jetbrains.kotlin.jvm") version "1.9.25"
        id("org.jetbrains.kotlin.kapt") version "1.9.25"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
