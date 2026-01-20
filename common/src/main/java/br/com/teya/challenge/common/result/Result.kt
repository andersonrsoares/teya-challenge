package br.com.teya.challenge.common.result

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val error: ResultErrorMessage): Result<T>()
}

inline fun <T> Result<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (ResultErrorMessage) -> Unit
){
    when (this) {
        is Result.Success -> onSuccess(data)
        is Result.Error -> onError(error)
    }
}