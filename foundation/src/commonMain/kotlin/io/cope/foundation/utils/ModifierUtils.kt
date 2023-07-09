package io.cope.foundation.utils

import io.cope.core.modifier.*

fun Modifier.hasSize(): Boolean{
    val list = toList()
    list.forEach { if (it is LayoutModifier) return true }
    return false
}
fun Modifier.hasWeight(): Boolean{
    toList().forEach { if (it is WeightModifier) return true }
    return false
}