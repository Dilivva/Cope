package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.geometry.Size

class WeightModifier(
    private val height: Float? = null,
    private val width: Float? = null,
): Modifier{


    @Suppress("Duplicates")
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
        if (width != other.width) return false
        return true
    }

    override fun hashCode(): Int {
        var result = height?.hashCode() ?: 0
        result = 31 * result + (width?.hashCode() ?: 0)
        return result
    }


}