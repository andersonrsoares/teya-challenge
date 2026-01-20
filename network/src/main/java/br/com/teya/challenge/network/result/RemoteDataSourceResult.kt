package br.com.teya.challenge.network.result

sealed class RemoteDataSourceResult<T> {
    data class Success<T>(val data: T): RemoteDataSourceResult<T>()
    data class Error<T>(val error: RemoteDataSourceError): RemoteDataSourceResult<T>()
}