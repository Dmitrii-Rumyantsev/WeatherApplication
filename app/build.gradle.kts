@file:Suppress("DEPRECATION")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.relay") version "0.3.12"
}

android {
    namespace = "com.example.weather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    android {
        packagingOptions {
            exclude("META-INF/DEPENDENCIES")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:1.1.0")
    implementation("androidx.compose.ui:ui-graphics:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0")
    implementation("androidx.compose.material3:material3:1.1.0-alpha02")
    implementation("com.google.firebase:firebase-crashlytics-gradle:2.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.google.guava:guava:27.0.1-android") // Keep only guava dependency
    implementation("io.ktor:ktor-server:2.3.9")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.9")
    // Room dependencies


    // Ktor dependencies
    implementation("io.ktor:ktor-client-core:2.3.9")
    implementation("io.ktor:ktor-client-android:2.3.9")
    implementation("io.ktor:ktor-client-json:2.3.9")
    implementation("io.ktor:ktor-serialization:2.3.9")
    testImplementation("io.ktor:ktor-client-mock-jvm:1.6.1")
    testImplementation("junit:junit:4.12")
}