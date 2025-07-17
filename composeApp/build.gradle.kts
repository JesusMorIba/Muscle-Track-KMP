import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // ML Kit
            implementation("com.google.mlkit:pose-detection-accurate:18.0.0-beta5")
            // CameraX core library
            implementation("androidx.camera:camera-core:1.4.1")
            // CameraX Camera2 extensions
            implementation("androidx.camera:camera-camera2:1.4.1")
            // CameraX Lifecycle library
            implementation("androidx.camera:camera-lifecycle:1.4.1")
            // CameraX View class
            implementation("androidx.camera:camera-view:1.4.1")

            // Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.0.4"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-android")

            // Ktor
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            api(compose.materialIconsExtended)
            api(compose.foundation)
            api(compose.animation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Camera Permission
            implementation("dev.icerock.moko:permissions-camera:0.19.1")

            // Support Permission
            implementation("dev.icerock.moko:permissions-compose:0.19.1")

            // UI Tooling Preview
            implementation("org.jetbrains.compose.components:components-ui-tooling-preview:1.8.0-beta01")

            // Dates
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")

            // Navigation PreCompose
            api("moe.tlaster:precompose:1.6.2")

            // ViewModel PreCompose
            api("moe.tlaster:precompose-viewmodel:1.6.2")

            // Coil
            implementation("io.coil-kt.coil3:coil-compose:3.1.0")
            implementation("io.coil-kt.coil3:coil-network-ktor3:3.1.0")

            // Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.0.4"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-compose")
            implementation("moe.tlaster:precompose-koin:1.6.2")

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.serialization)

            // Chart
            implementation("io.github.thechance101:chart:1.1.0")
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.jmoriba.muscletrack"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jmoriba.muscletrack"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

