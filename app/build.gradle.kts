import org.jetbrains.kotlin.cli.js.internal.main

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.relay") version "0.3.12"
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.9.22"
}
kapt {

    useBuildCache = true
    correctErrorTypes = true
}

android {
    namespace = "com.example.weather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.constraintlayout.compose.android)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:1.1.0")
    implementation("androidx.compose.ui:ui-graphics:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0")
    implementation("androidx.compose.material3:material3:1.1.0-alpha02")
    implementation("com.google.firebase:firebase-crashlytics-gradle:2.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.google.guava:guava:27.0.1-android")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.10")
    implementation("io.ktor:ktor-serialization-gson:2.3.10")
    implementation("io.ktor:ktor-client:2.3.10")
    implementation("io.ktor:ktor-client-android:2.3.10")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.19.0")


    implementation("androidx.compose.foundation:foundation:1.4.3")

    // Room dependencies

    // Ktor dependencies
    testImplementation("junit:junit:4.12")
    debugImplementation(libs.ui.tooling)
}
