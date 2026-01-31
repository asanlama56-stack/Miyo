plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.miyo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.miyo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packagingOptions {
        resources {
            excludes += setOf("META-INF/DEPENDENCIES", "META-INF/LICENSE")
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // App modules
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":extensions"))

    // Desugaring for NIO and Java 8+ features
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    // Kotatsu parsers (placeholder version; exclude org.json on Android)
    implementation("com.github.KotatsuApp:kotatsu-parsers:main-SNAPSHOT") {
        exclude(group = "org.json", module = "json")
    }

    // Room database
    implementation("androidx.room:room-runtime:2.6.0")

    // OkHttp for network requests
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.5.0")
}
