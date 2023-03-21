package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.Color

class Background(private val color: Color): Modifier {

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        doodleNode.backgroundColor = color
    }

    override fun toString(): String {
        return "Background"
    }
}

data class Border(val width: Int, val color: Color)

fun Modifier.background(color: Color) = then(Background(color))

fun Modifier.border(size: Int, color: Color): Modifier{
    val border = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
           doodleNode.border = Border(size, color)
        }
    }

    return then(border)
}

