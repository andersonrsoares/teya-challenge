package br.com.teya.challenge.network.result

sealed class RemoteDataSourceError(val exception: Throwable) {
    class ServerError(exception: Throwable, val code: Int) : RemoteDataSourceError(exception)
    class NetworkError(exception: Throwable) : RemoteDataSourceError(exception)
}