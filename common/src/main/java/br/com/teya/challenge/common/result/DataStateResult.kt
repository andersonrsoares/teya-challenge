package br.com.teya.challenge.common.result

import br.com.teya.challenge.common.R
import br.com.teya.challenge.common.network.RemoteDataSourceNetworkException
import br.com.teya.challenge.common.network.RemoteDataSourceServerException
import br.com.teya.challenge.common.network.RemoteDataSourceUnknownException

sealed class DataStateResult<T> {
    data class Success<T>(val data: T): DataStateResult<T>()
    data class Error<T>(val error: ResultErrorMessage): DataStateResult<T>()
}

inline fun <T> DataStateResult<T>.fold(
    onSuccess: (T) -> Unit,
    onFailure: (ResultErrorMessage) -> Unit
): DataStateResult<T> {
    when (this) {
        is DataStateResult.Success -> {
            onSuccess(data)
        }
        is DataStateResult.Error -> {
            onFailure(error)
        }
    }
    return this
}

inline fun <T> Result<T>.mapToDataStateResult(
    onSuccess: (T) -> Unit = {},
    onFailure: (ResultErrorMessage) -> Unit = {},
): DataStateResult<T> = this.fold(
    onSuccess = {
        onSuccess(it)
        DataStateResult.Success(it)
    },
    onFailure = {
        onFailure(it.toResultErrorMessage())
        DataStateResult.Error(error = it.toResultErrorMessage())
    }
)

fun Throwable.toResultErrorMessage(): ResultErrorMessage = when (this) {
    is RemoteDataSourceServerException -> ResultErrorMessage.ResourceMessage(R.string.error_server)
    is RemoteDataSourceNetworkException -> ResultErrorMessage.ResourceMessage(R.string.error_network_issue)
    is RemoteDataSourceUnknownException -> ResultErrorMessage.ResourceMessage(R.string.error_unknown)
    else -> ResultErrorMessage.ResourceMessage(R.string.error_unknown)
}