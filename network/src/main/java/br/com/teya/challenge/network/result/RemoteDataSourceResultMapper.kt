package br.com.teya.challenge.network.result

import br.com.teya.challenge.common.result.ResultErrorMessage
import br.com.teya.challenge.common.result.Result
inline fun <T, R> RemoteDataSourceResult<T>.map(
    transform: (T) -> R
): RemoteDataSourceResult<R> =
    when (this) {
        is RemoteDataSourceResult.Success ->
            RemoteDataSourceResult.Success(transform(data))

        is RemoteDataSourceResult.Error -> RemoteDataSourceResult.Error(this.error)
    }

inline fun <T> RemoteDataSourceResult<T>.mapToResult(
    transform: (RemoteDataSourceError) -> ResultErrorMessage,
): Result<T> =
    when (this) {
        is RemoteDataSourceResult.Success ->
            Result.Success(data)

        is RemoteDataSourceResult.Error -> Result.Error(transform(error))
    }

inline fun RemoteDataSourceError.toResultErrorMessage(
    onServerError: (RemoteDataSourceError.ServerError) -> ResultErrorMessage,
    onNetworkError: (RemoteDataSourceError.NetworkError) -> ResultErrorMessage,
): ResultErrorMessage {
   return when(this) {
        is RemoteDataSourceError.NetworkError -> onNetworkError(this)
        is RemoteDataSourceError.ServerError -> onServerError(this)
    }
}