plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"    // destination
    id("kotlin-parcelize")     // parcelization
    kotlin("plugin.serialization") version "2.0.21"    // serialization
}

android {
    namespace = "com.example.rhymefinder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rhymefinder"
        minSdk = 34
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.test.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("io.github.raamcosta.compose-destinations:core:1.9.63")    // destination
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.63")    // destination
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")    // serialization
    implementation("io.ktor:ktor-client-core:2.3.3")    // serialization
    implementation("io.ktor:ktor-client-cio:2.3.3")    // serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")    // serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.3.3")    // serialization
    implementation("io.coil-kt.coil3:coil-compose:3.0.4")    // serialization
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")    // serialization
    implementation("com.orhanobut:hawk:2.0.1")    // hawk library
}