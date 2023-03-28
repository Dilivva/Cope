@file:Suppress("unused")
package io.coodle.core.modifier

import androidx.compose.runtime.Stable
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

    override fun equals(other: Any?): Boolean {
        val otherFillMaxSize = other as FillMaxSize
        return this === otherFillMaxSize
    }
}

class FillMaxWidth(private val fraction: Int): Modifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.width > 0) doodleNode.width = (it.width / fraction) - doodleNode.padding.horizontal
            doodleNode.fixedWidth = true
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherFill = other as FillMaxWidth
        return this.fraction == otherFill.fraction
    }

    override fun hashCode(): Int {
        return fraction
    }

}
class FillMaxHeight(private val fraction: Int): Modifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.height > 0) doodleNode.height = (it.height / fraction) - doodleNode.padding.vertical
            doodleNode.fixedHeight = true
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherFill = other as FillMaxHeight
        return this.fraction == otherFill.fraction
    }

    override fun hashCode(): Int {
        return fraction
    }
}

class SizeModifier(
    private val height: Int? = null,
    private val width: Int? = null
): Modifier{

    override fun apply(doodleNode: DoodleNode) {
        if (height != null && width != null){
            doodleNode.positionable?.let {
                doodleNode.size = Size(
                    width -  doodleNode.padding.horizontal,
                    height - doodleNode.padding.vertical
                )
                doodleNode.fixedSize = true
            }
            return
        }
        height?.let {
            doodleNode.positionable?.let {
                doodleNode.height = height.toDouble() - doodleNode.padding.vertical
                doodleNode.fixedHeight = true
            }
        }
        width?.let {
            doodleNode.positionable?.let {
                doodleNode.width = width.toDouble() - doodleNode.padding.horizontal
                doodleNode.fixedWidth = true
            }
        }

    }

    override fun equals(other: Any?): Boolean {
        val sizeModifier = other as SizeModifier
        return this.height == sizeModifier.height &&
                this.width == sizeModifier.width
    }

    override fun hashCode(): Int {
        var result = height ?: 0
        result = 31 * result + (width ?: 0)
        return result
    }


}


fun Modifier.size(height: Int, width: Int): Modifier{
    return then(SizeModifier(height, width))
}
fun Modifier.size(size: Int): Modifier{
    return then(SizeModifier(size, size))
}

fun Modifier.fillMaxSize(): Modifier{
    return then(FillMaxSize)
}
@Stable
fun Modifier.fillMaxWidth(fraction: Int = 1): Modifier{
    return then(FillMaxWidth(fraction))
}
fun Modifier.fillMaxHeight(fraction: Int = 1): Modifier{
    return then(FillMaxHeight(fraction))
}

fun Modifier.width(width: Int): Modifier{
    return then(SizeModifier(width = width))
}
@Stable
fun Modifier.height(height: Int): Modifier{
    return then(SizeModifier(height = height))
}


