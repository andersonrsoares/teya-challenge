package br.com.teya.challenge.common

import br.com.teya.challenge.common.result.ResultErrorMessage

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val error: ResultErrorMessage): Result<T>()
}