package io.cope.core.layout

import io.nacular.doodle.geometry.Size

data class Constraints(
    var maxHeight: Double = 0.0,
    var maxWidth: Double = 0.0
)

fun withInConstraint(size: Double, maxSize: Double): Double{
    return size.coerceAtMost(maxSize)
}

fun Double.orMax(max: Double) = takeIf { it != 0.0 } ?: max
fun Size.orMax(max: Size) = takeIf { !it.empty } ?: max
