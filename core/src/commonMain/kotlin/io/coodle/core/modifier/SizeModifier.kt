@file:Suppress("unused")
package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.geometry.Size


object FillMaxSize: Modifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        //Remember padding
        doodleNode.positionable?.let {
            if (it.width > 0 && it.height > 0) {
                val width = it.width - doodleNode.padding.horizontal
                val height = it.height - doodleNode.padding.vertical
                doodleNode.size = Size(width, height)
                doodleNode.fixedSize = true
            }
        }
    }
}
object FillMaxWidth: Modifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.width > 0) doodleNode.width = it.width - doodleNode.padding.horizontal
            doodleNode.fixedWidth = true
        }
    }

}
object FillMaxHeight: Modifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.height > 0) doodleNode.height = it.height - doodleNode.padding.vertical
            doodleNode.fixedHeight = true
        }
    }
}


fun Modifier.size(height: Int, width: Int): Modifier{
    val size = object: Modifier{
        override fun apply(
            doodleNode: DoodleNode
        ) {
            doodleNode.positionable?.let {
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
            doodleNode: DoodleNode
        ) {
            doodleNode.positionable?.let {
                doodleNode.size = Size(size.value, size.value)
                doodleNode.fixedSize = true
            }
        }
    }
    return then(sizeImpl)
}

fun Modifier.fillMaxSize(): Modifier{
    return then(FillMaxSize)
}
fun Modifier.fillMaxWidth(): Modifier{
    return then(FillMaxWidth)
}
fun Modifier.fillMaxWidth(fraction: Int): Modifier{
    val fillMaxWidthWithFraction = object: Modifier{
        override fun apply(
            doodleNode: DoodleNode
        ) {
            doodleNode.positionable?.let {
                val fractionOfParent = it.width / fraction
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
            doodleNode: DoodleNode
        ) {
            doodleNode.positionable?.let {
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
            doodleNode: DoodleNode
        ) {
            doodleNode.positionable?.let {
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


