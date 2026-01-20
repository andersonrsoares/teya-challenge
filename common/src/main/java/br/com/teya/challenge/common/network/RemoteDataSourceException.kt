package br.com.teya.challenge.common.network

open class RemoteDataSourceException(exception: Throwable? = null): Throwable(exception)

class RemoteDataSourceServerException(val code: Int) : RemoteDataSourceException()
class RemoteDataSourceNetworkException(exception: Throwable) : RemoteDataSourceException(exception)