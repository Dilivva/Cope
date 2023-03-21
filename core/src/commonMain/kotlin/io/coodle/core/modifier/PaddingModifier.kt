@file:Suppress("unused")
package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View

data class Padding(
    val top: Int,
    val bottom: Int,
    val left: Int,
    val right: Int
): Modifier {

    val vertical = top + bottom
    val horizontal = left + right

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        doodleNode.padding = this
    }


    override fun toString(): String {
        return "Padding"
    }
}

fun Modifier.padding(
    top: Int = 0,
    bottom: Int = 0,
    left: Int = 0,
    right: Int = 0
) = then(Padding(top, bottom, left, right))

fun Modifier.padding(all: Int = 0) = then(Padding(all, all, all, all))

fun Modifier.padding(vertical: Int = 0, horizontal: Int = 0): Modifier {
    val padding = Padding(top = vertical, bottom = vertical, left = horizontal, right = horizontal)
    return then(padding)
}