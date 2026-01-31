plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(project(":core"))
    implementation("com.github.KotatsuApp:kotatsu-parsers:main-SNAPSHOT")
}
