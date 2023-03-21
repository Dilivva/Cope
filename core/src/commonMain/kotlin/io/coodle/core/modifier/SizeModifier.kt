package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.geometry.Size

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
object FillMaxSize: Modifier {

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        //Remember padding
        parent?.let {
            if (parent.width > 0 && parent.height > 0) {
                val width = parent.width - doodleNode.padding.horizontal
                val height = parent.height - doodleNode.padding.vertical
                doodleNode.size = Size(width, height)
                doodleNode.fixedSize = true
            }
        }
    }
}
object FillMaxWidth: Modifier {

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        parent?.let {
            if (parent.width > 0) doodleNode.width = parent.width - doodleNode.padding.horizontal
            doodleNode.fixedWidth = true
        }
    }

}
object FillMaxHeight: Modifier {

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        parent?.let {
            if (parent.height > 0) doodleNode.height = parent.height - doodleNode.padding.vertical
            doodleNode.fixedHeight = true
        }
    }
}


fun Modifier.size(height: Int, width: Int): Modifier{
    val size = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            parent?.let {
                doodleNode.size = Size(width, height)
                doodleNode.fixedSize = true
            }
        }
    }
    return then(size)
}
fun Modifier.size(size: Dp): Modifier{
    val sizeImpl = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            parent?.let {
                doodleNode.size = Size(size.value, size.value)
                doodleNode.fixedSize = true
            }
        }
    }
    return then(sizeImpl)
}

fun Modifier.padding(
    top: Int = 0,
    bottom: Int = 0,
    left: Int = 0,
    right: Int = 0
) = then(Padding(top, bottom, left, right))

fun Modifier.padding(
    all: Int = 0
) = then(Padding(all, all, all, all))

fun Modifier.fillMaxSize(): Modifier{
    return then(FillMaxSize)
}
fun Modifier.fillMaxWidth(): Modifier{
    return then(FillMaxWidth)
}
fun Modifier.fillMaxWidth(fraction: Int): Modifier{
    val fillMaxWidthWithFraction = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            parent?.let {
                val fractionOfParent = parent.width / fraction
                doodleNode.width = fractionOfParent
                doodleNode.fixedWidth = true
            }
        }
    }
    return then(fillMaxWidthWithFraction)
}
fun Modifier.fillMaxHeight(): Modifier{
    return then(FillMaxHeight)
}

fun Modifier.width(width: Dp): Modifier{
    val widthImpl = object: Modifier{

        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            parent?.let {
                doodleNode.width = width.value
                doodleNode.fixedWidth = true
            }
        }

    }
    return then(widthImpl)
}
fun Modifier.height(height: Dp): Modifier{
    val heightImpl = object: Modifier{

        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            parent?.let {
                doodleNode.height = height.value
                doodleNode.fixedHeight = true
            }
        }

        override fun toString(): String {
            return "Height"
        }
    }
    return then(heightImpl)
}


