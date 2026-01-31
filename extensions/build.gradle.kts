plugins {
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(project(":core"))
    implementation("com.github.KotatsuApp:kotatsu-parsers:main-SNAPSHOT")
}
