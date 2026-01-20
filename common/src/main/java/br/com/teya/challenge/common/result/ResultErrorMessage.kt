package br.com.teya.challenge.common.result

sealed class ResultErrorMessage() {
    data class StringMessage(val message: String): ResultErrorMessage()
    data class ResourceMessage(val message: Int): ResultErrorMessage()
}