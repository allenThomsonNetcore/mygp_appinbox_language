plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mygp.composeactivity"
    compileSdk = 35

    buildFeatures {
        compose = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    defaultConfig {
        applicationId = "com.mygp.composeactivity"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("com.netcore.android:smartech-push:3.5.6")
    implementation("com.netcore.android:smartech-sdk:3.6.2")
    implementation(libs.smartech.sdk)
    implementation ("com.netcore.android:smartech-nudges-compose:10.5.4")
    implementation(platform("com.google.firebase:firebase-bom:34.0.0"))
    implementation(libs.androidx.compose.ui)
    implementation ("com.netcore.android:smartech-appinbox:3.5.2")
    implementation("com.google.firebase:firebase-messaging:24.1.2")
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.runtime)

    // Jetpack Compose Navigation
    implementation (libs.androidx.navigation.compose)

// DataStore Preferences
    implementation (libs.androidx.datastore.preferences)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.material3.android)
   // implementation(libs.firebase.messaging.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}