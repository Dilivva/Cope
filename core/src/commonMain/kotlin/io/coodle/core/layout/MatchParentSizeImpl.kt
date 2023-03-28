package io.coodle.core.layout

import io.coodle.core.modifier.Modifier
import io.coodle.core.node.DoodleNode

class MatchParentSizeImpl: Modifier {

    override fun apply(doodleNode: DoodleNode) {
        doodleNode.verticalWeight = 1f
        doodleNode.horizontalWeight = 1f
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        return true
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }


}