plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "br.com.teya.challenge.top.albums.domain"
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
    implementation(project(":topAlbums:data"))
    implementation(project(":common"))

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // koin
    implementation(libs.koin.android)
}