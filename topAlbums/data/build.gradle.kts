plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "br.com.teya.top.albums.data"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 29
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    api(project(":network"))

    // retrofit
    implementation(libs.retrofit.core)

    // Moshi
    implementation(libs.moshi.core)
    ksp(libs.moshi.codegen)

    // retrofit
    implementation(libs.koin.android)

    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}