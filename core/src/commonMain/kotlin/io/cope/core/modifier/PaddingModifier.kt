@file:Suppress("unused")
package io.cope.core.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import io.cope.core.node.DoodleNode

@Immutable
data class Padding(
    val top: Int,
    val bottom: Int,
    val left: Int,
    val right: Int
): Modifier {

    val vertical = top + bottom
    val horizontal = left + right

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.padding = this
        applyPadding(doodleNode)
    }

    private fun applyPadding(doodleNode: DoodleNode){
        if (vertical != 0 && doodleNode.fixedHeight){
            val childSize = doodleNode.height - vertical
            println("Height: $childSize, Fixed: ${doodleNode.height}")
            doodleNode.view.height = if (childSize > 0) childSize else 0.0
            doodleNode.container.height = doodleNode.height
        }
        if (horizontal != 0 && doodleNode.fixedWidth){
            val childSize = doodleNode.width - horizontal
            println("Height: $childSize, Fixed: ${doodleNode.width}")
            doodleNode.view.width = if (childSize > 0) childSize else 0.0
            doodleNode.container.width = doodleNode.width
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherPadding = other as Padding
        return this.top == otherPadding.top &&
                this.bottom == otherPadding.bottom &&
                this.left == otherPadding.left &&
                this.right == otherPadding.right &&
                this.vertical == otherPadding.vertical &&
                this.horizontal == otherPadding.horizontal
    }


    override fun toString(): String {
        return "[$left,$top,$right,$bottom]"
    }

    override fun hashCode(): Int {
        var result = top
        result = 31 * result + bottom
        result = 31 * result + left
        result = 31 * result + right
        result = 31 * result + vertical
        result = 31 * result + horizontal
        return result
    }
}

fun Modifier.padding(
    top: Int = 0,
    bottom: Int = 0,
    left: Int = 0,
    right: Int = 0
) = then(Padding(top, bottom, left, right))

@Stable
fun Modifier.padding(all: Int = 0) = then(Padding(all, all, all, all))

@Stable
fun Modifier.padding(
    vertical: Int = 0,
    horizontal: Int = 0
): Modifier {
    val padding = Padding(top = vertical, bottom = vertical, left = horizontal, right = horizontal)
    return then(padding)
}