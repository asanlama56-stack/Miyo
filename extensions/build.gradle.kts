plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(project(":core"))
    implementation("com.github.KotatsuApp:kotatsu-parsers:main-SNAPSHOT")
}
