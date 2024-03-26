plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.proximity_connect"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proximity_connect"
        minSdk = 27
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
    implementation("com.google.android.material:material:1.2.0")
    implementation("com.google.android.gms:play-services-gcm:17.0.0")
    implementation("com.android.volley:volley:1.2.1")
    val fragment_version ="1.6.1"
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:5.1.49")
    //hamcrest
    testImplementation("org.hamcrest:hamcrest-library:2.2")
    implementation("androidx.fragment:fragment:$fragment_version")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("no.nordicsemi.android.kotlin.ble:scanner:1.0.15")
    implementation("no.nordicsemi.android.kotlin.ble:client:1.0.15")
    implementation("no.nordicsemi.android.kotlin.ble:advertiser:1.0.15")
    implementation("no.nordicsemi.android.kotlin.ble:server:1.0.15")
    implementation("no.nordicsemi.android.common:analytics:1.9.4")
    implementation("no.nordicsemi.android.common:navigation:1.9.4")
    implementation("no.nordicsemi.android.kotlin.ble:core:1.0.15")
    implementation("no.nordicsemi.android.kotlin.ble:logger:0.2.1")
    implementation("androidx.compose.material:material-icons-extended:1.6.2")
    implementation("no.nordicsemi.android.common:theme:1.9.4")
    implementation("no.nordicsemi.android.kotlin.ble:advertiser:1.0.15")
    implementation("no.nordicsemi.android.common:analytics:1.9.4")
    implementation("com.github.jeziellago:compose-markdown:0.3.6")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.lifecycle:lifecycle-common:2.8.0-alpha02")
    implementation("no.nordicsemi.android.common:permissions-ble:1.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0-alpha02")
    implementation("no.nordicsemi.android.kotlin.ble:profile:1.0.15")
    implementation("no.nordicsemi.android.kotlin.ble:core:1.0.15")
    implementation("no.nordicsemi.android.kotlin.ble:uiscanner:1.0.15")
    implementation("no.nordicsemi.android.common:theme:1.9.4")
    implementation("androidx.activity:activity:1.9.0-alpha03")
    implementation("androidx.multidex:multidex:2.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}