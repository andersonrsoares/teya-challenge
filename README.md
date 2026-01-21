# Teya Challenge
[![Android CI](https://github.com/andersonrsoares/teya-challenge/actions/workflows/android.yml/badge.svg?branch=main)](https://github.com/andersonrsoares/teya-challenge/actions/workflows/android.yml)

This is an Android application that displays a list of the top albums from the iTunes Store.

## Architecture

The app is built using the Model-View-Intent (MVI) architectural pattern. The app is divided into the following modules:

*   `:app`: The main application module that integrates all the other modules.
*   `:common`: A module that contains common utilities and components that are shared across the other modules.
*   `:network`: A module that handles all the network requests.
*   `:topAlbums`: A module that contains the following sub-modules:
    *   `:data`: The data layer of the `topAlbums` feature.
    *   `:domain`: The domain layer of the `topAlbums` feature.
    *   `:presentation`: The presentation layer of the `topAlbums` feature.

The app uses `Koin` for dependency injection.

## Libraries

*   **UI**:
    *   `Jetpack Compose`: For building the UI.
    *   `Coil`: For loading images.
*   **Architecture**:
    *   `ViewModel`: For managing the state of the UI.
    *   `Kotlin Coroutines`: For managing background threads.
    *   `Moshi`: For parsing JSON.
    *   `Koin`: For dependency injection.
*   **Networking**:
    *   `Retrofit`/`OkHttp`: For making network requests.
*   **Testing**:
    *   `JUnit`: For unit testing.
    *   `Mockk`: For mocking objects.
    *   `Turbine`: For testing `Flows`.
