package io.cope.core.layout

import io.cope.core.node.DoodleNode

class WeightsImpl {
    private var weights = 0f
    private var totalMeasured = 0.0

    fun setWeightedChildrenAndWeights(weights: Float, totalMeasured: Double){
        if (totalMeasured != Double.POSITIVE_INFINITY){
            this.totalMeasured = totalMeasured
        }
        this.weights = weights
    }

    fun applyWeightsForRowOrColumn(
        doodleViews: List<DoodleNode>,
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
            if (isRow){
                it.width = applyPaddingHorizontal(it,eachWeight * it.horizontalWeight)
            }
            else it.height = applyPaddingVertical(it, eachWeight * it.verticalWeight)
        }
    }

    private fun applyPaddingHorizontal(doodleNode: DoodleNode, width: Double): Double{
        val calculatedWidth = width - doodleNode.padding.horizontal
        doodleNode.container.width = width
        doodleNode.view.width = calculatedWidth
        return width
    }
    private fun applyPaddingVertical(doodleNode: DoodleNode, height: Double): Double{
        val calculatedHeight = height - doodleNode.padding.vertical
        doodleNode.container.height = height
        doodleNode.view.height = calculatedHeight
        return height
    }
    private fun getEachWeight(parentWidthOrHeight: Double): Double {
        val remaining = parentWidthOrHeight - totalMeasured
        return remaining / weights

    }

    private fun getParentWidthOrHeight(parent: DoodleNode, isRow: Boolean) =
        when{
            isRow -> if (parent.fixedWidth) parent.width else parent.maxSize.width - parent.padding.horizontal
            else -> if (parent.fixedHeight) parent.height else parent.maxSize.height - parent.padding.vertical
        }
    private fun getWeightedChildren(doodleViews: List<DoodleNode>, isRow: Boolean) =
        when{
            isRow -> doodleViews.filter { it.horizontalWeight > 0f  }
            else -> doodleViews.filter { it.verticalWeight > 0f }
        }


    /**
     * Check if weight can be applied by making sure there's no
     * declared size, to avoid infinite calls
     *
     * @param doodleViews [List]
     * @return [Boolean]
     */
    private fun canApplyWeight(doodleViews: List<DoodleNode>): Boolean{
        return weights != 0f &&
                (doodleViews.any { it.horizontalWeight > 0f && it.width == 0.0 }||
                        doodleViews.any { it.verticalWeight > 0f && it.height == 0.0 })
    }
}