package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.geometry.Size

class WeightModifier(
    private val height: Float? = null,
    private val width: Float? = null,
    private val totalMeasured: Double,
    private val weights: Float
): Modifier{

    @Suppress("Duplicates")
    override fun apply(doodleNode: DoodleNode) {
        height?.let {
            doodleNode.verticalWeight = it
            val positionable = doodleNode.positionable
            if (positionable != null && positionable.size.has()){
                val remaining = positionable.height - totalMeasured
                val eachWeight = remaining / weights
                doodleNode.height = eachWeight * doodleNode.verticalWeight
            }
        }
        width?.let {
            println("TotalMeasured: $totalMeasured, weights: $weights")
            doodleNode.horizontalWeight = width
            val positionable = doodleNode.positionable
            if (positionable != null && positionable.size.has()){
                val remaining = positionable.width - totalMeasured
                val eachWeight = remaining / weights
                doodleNode.width = eachWeight * doodleNode.horizontalWeight
            }
        }
    }
    private fun Size.has(): Boolean{
        return width > 0.0 && height > 0.0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WeightModifier

        if (height != other.height) return false
        if (width != other.width) return false
        if (totalMeasured != other.totalMeasured) return false
        if (weights != other.weights) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height?.hashCode() ?: 0
        result = 31 * result + (width?.hashCode() ?: 0)
        result = 31 * result + totalMeasured.hashCode()
        result = 31 * result + weights.hashCode()
        return result
    }


}