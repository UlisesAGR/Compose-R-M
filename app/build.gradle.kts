plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.devtoolsKsp)
}

android {
    namespace = "com.compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.compose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "DATABASE_NAME", "\"database\"")
        buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
        buildConfigField("String", "CHARACTER", "\"character\"")
    }

    buildTypes {
        release {
            isDebuggable = false
            // Optimize Code
            isMinifyEnabled = true
            // Optimize Resource
            isShrinkResources = true
            // Optimize Includes
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            resValue("string", "app_name", "Compose")
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            resValue("string", "app_name", "Compose Debug")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //core
    implementation(libs.androidx.core.ktx)
    implementation(libs.org.coroutines)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")
    //ui
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.foundation:foundation:1.6.7")
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.7")
    //library
    implementation(libs.bundles.androidx.material.desing)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.com.retrofit)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.bundles.androidx.room)
    ksp(libs.room.compiler)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("nl.dionsegijn:konfetti-compose:2.0.4")
    //test
    testImplementation(libs.junit)
    testImplementation(libs.org.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
