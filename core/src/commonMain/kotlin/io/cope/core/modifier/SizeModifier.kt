@file:Suppress("unused")
package io.cope.core.modifier

import androidx.compose.runtime.Stable
import io.cope.core.node.DoodleNode
import io.nacular.doodle.geometry.Size


object FillMaxSize: LayoutModifier {
    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.width > 0 && it.height > 0) {
                val size = Size(it.width, it.height)
                doodleNode.size = applyPadding(doodleNode, size)
                doodleNode.fixedSize = true

            }
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherFillMaxSize = other as FillMaxSize
        return this === otherFillMaxSize
    }
}

class FillMaxWidth(private val fraction: Int): LayoutModifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.width > 0) {
                val width = it.width / fraction
                doodleNode.width = applyPaddingHorizontal(doodleNode, width)
            }
            doodleNode.fixedWidth = true
        }
    }



    override fun toString(): String {
        return "FillMaxSize"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as FillMaxWidth

        return fraction == other.fraction
    }

    override fun hashCode(): Int {
        return fraction
    }

}
class FillMaxHeight(private val fraction: Int): LayoutModifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.positionable?.let {
            if (it.height > 0) {
                val height = it.height / fraction
                doodleNode.height = applyPaddingVertical(doodleNode, height)
            }
            doodleNode.fixedHeight = true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as FillMaxHeight

        return fraction == other.fraction
    }

    override fun hashCode(): Int {
        return fraction
    }


}

class SizeModifier(
    private val height: Int? = null,
    private val width: Int? = null,
    private val minWidth: Int? = null,
    private val minHeight: Int? = null
): LayoutModifier{

    override fun apply(doodleNode: DoodleNode) {
        height?.let {
            doodleNode.positionable?.let {
                doodleNode.height = applyPaddingVertical(doodleNode, height.toDouble())
                doodleNode.fixedHeight = true
            }
        }
        width?.let {
            doodleNode.positionable?.let {
                doodleNode.width = applyPaddingHorizontal(doodleNode, width.toDouble())
                doodleNode.fixedWidth = true
            }
        }

        minWidth?.let {
            doodleNode.minWidth = if (it <= doodleNode.maxSize.width)  it.toDouble()
            else  doodleNode.maxSize.width
        }
        minHeight?.let {
            doodleNode.minHeight = if (it <= doodleNode.maxSize.height)  it.toDouble()
            else  doodleNode.maxSize.height
        }

        doodleNode.fixedSize = width != null && height != null


    }



    override fun hashCode(): Int {
        var result = height ?: 0
        result = 31 * result + (width ?: 0)
        return result
    }

    @Suppress("Duplicates")
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SizeModifier

        if (height != other.height) return false
        if (width != other.width) return false
        if (minWidth != other.minWidth) return false
        return minHeight == other.minHeight
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

fun Modifier.minWidth(width: Int): Modifier{
    return then(SizeModifier(minWidth = width))
}
fun Modifier.minHeight(height: Int): Modifier{
    return then(SizeModifier(minHeight = height))
}
fun Modifier.minSize(width: Int, height: Int): Modifier{
    return then(SizeModifier(minWidth = width, minHeight = height))
}

