package br.com.teya.challenge.common.result

import androidx.annotation.StringRes
import br.com.teya.challenge.common.composables.UiText

sealed class ResultErrorMessage() {
    data class StringMessage(val message: String): ResultErrorMessage()
    data class ResourceMessage(@param:StringRes val message: Int): ResultErrorMessage()
}

fun ResultErrorMessage.toUiText(): UiText {
   return when (this) {
        is ResultErrorMessage.StringMessage -> {
            UiText.DynamicString(this.message)
        }
        is ResultErrorMessage.ResourceMessage -> {
            UiText.ResourceString(this.message)
        }
    }
}