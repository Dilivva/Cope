package io.coodle.core.layout

import io.coodle.core.node.DoodleNode

class WeightsImpl {
    private var weights = 0f
    private var totalMeasured = 0.0

    fun setWeightedChildrenAndWeights(weights: Float, totalMeasured: Double){
        if (totalMeasured != Double.POSITIVE_INFINITY){
            this.totalMeasured = totalMeasured
        }
        this.weights = weights
        println("Total: $totalMeasured, $weights")
    }

    fun applyWeightsForRowOrColumn(
        doodleViews: MutableList<DoodleNode>,
        parent: DoodleNode,
        isRow: Boolean
    ){
        if (canApplyWeight(doodleViews)){
            val weightedChildren = getWeightedChildren(doodleViews, isRow)
            val positionable = getParentWidthOrHeight(parent, isRow)
            val eachWeight = getEachWeight(positionable)
            apply(eachWeight, weightedChildren, isRow)
        }
    }

    private fun apply(eachWeight: Double, doodleViews: List<DoodleNode>, isRow: Boolean){
        doodleViews.forEach {
            if (isRow) it.width = (eachWeight * it.horizontalWeight) - it.padding.horizontal
            else it.height = (eachWeight * it.verticalWeight) - it.padding.vertical
        }
    }
    private fun getEachWeight(parentWidthOrHeight: Double): Double {
        val remaining = parentWidthOrHeight - totalMeasured
        return remaining / weights

    }

    private fun getParentWidthOrHeight(parent: DoodleNode, isRow: Boolean) =
        when{
            isRow -> if (parent.fixedWidth) parent.width else parent.maxSize.width
            else -> if (parent.fixedHeight) parent.height else parent.maxSize.height
        }
    private fun getWeightedChildren(doodleViews: MutableList<DoodleNode>, isRow: Boolean) =
        when{
            isRow -> doodleViews.filter { it.horizontalWeight > 0f  }
            else -> doodleViews.filter { it.verticalWeight > 0f }
        }
    private fun canApplyWeight(doodleViews: MutableList<DoodleNode>): Boolean{
        return weights != 0f &&
                totalMeasured != 0.0 &&
                (doodleViews.any { it.horizontalWeight > 0f && it.width == 0.0 }||
                        doodleViews.any { it.verticalWeight > 0f && it.height == 0.0 })
    }
}