package io.coodle.core.modifier

val Int.dp get() = Dp(this.toDouble())

data class Dp(val value: Double)