plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "br.com.teya.top.albums"
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
    implementation(project(":common"))
    implementation(project(":topAlbums:data"))
    implementation(project(":topAlbums:domain"))
    implementation(project(":topAlbums:presentation"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // retrofit
    implementation(libs.retrofit.core)

    // Moshi
    implementation(libs.moshi.core)
    ksp(libs.moshi.codegen)

    // retrofit
    implementation(libs.koin.android)
}