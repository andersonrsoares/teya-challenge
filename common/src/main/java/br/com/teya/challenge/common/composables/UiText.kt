package br.com.teya.challenge.common.composables

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.platform.LocalContext

@Immutable
sealed class UiText {

    @Immutable
    class DynamicString(
        val text: String?,
    ) : UiText()

    @Immutable
    class ResourceString(
        @param:StringRes val resId: Int,
    ) : UiText()
}

internal fun UiText.getString(context: Context): String =
    when (this) {
        is UiText.DynamicString -> this.text.orEmpty()
        is UiText.ResourceString -> context.getString(this.resId)
    }

@Composable
fun UiText.asString(): String = this.getString(LocalContext.current)
