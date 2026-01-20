package br.com.teya.challenge.common.network

open class RemoteDataSourceException(exception: Throwable? = null): Throwable(exception)

class RemoteDataSourceServerException(
    override val message: String? = null
) : RemoteDataSourceException(Throwable(message))

class RemoteDataSourceNetworkException(exception: Throwable) : RemoteDataSourceException(exception)

class RemoteDataSourceUnknownException(exception: Throwable) : RemoteDataSourceException(exception)