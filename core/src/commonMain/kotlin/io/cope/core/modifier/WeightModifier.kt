package io.cope.core.modifier

import io.cope.core.node.DoodleNode

class WeightModifier(
    val height: Float? = null,
    val width: Float? = null,
): Modifier{



    override fun apply(doodleNode: DoodleNode) {
        height?.let {
            doodleNode.verticalWeight = it
        }
        width?.let {
            doodleNode.horizontalWeight = it
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WeightModifier

        if (height != other.height) return false
        return width == other.width
    }

    override fun hashCode(): Int {
        var result = height?.hashCode() ?: 0
        result = 31 * result + (width?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "WeightModifier(height=$height, width=$width)"
    }


}