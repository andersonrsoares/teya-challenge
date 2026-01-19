package br.com.teya.challenge.network

sealed class RemoteDataSourceResult<T> {
    class Success<T>(val data: T): RemoteDataSourceResult<T>()
    class Error<T>(val error: RemoteDataSourceError): RemoteDataSourceResult<T>()
}