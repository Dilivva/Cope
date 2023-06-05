package io.cope.core.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
val Int.dp get() = Dp(this.toDouble())

@Immutable
data class Dp(val value: Double)