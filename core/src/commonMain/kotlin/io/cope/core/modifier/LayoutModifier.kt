package io.cope.core.modifier

import io.cope.core.node.DoodleNode
import io.nacular.doodle.geometry.Size

interface LayoutModifier: Modifier {
    fun applyPadding(doodleNode: DoodleNode, size: Size): Size{
        val calculatedHeight = size.height + doodleNode.padding.vertical
        val calculatedWidth = size.width + doodleNode.padding.horizontal
        val calculatedSize = Size(calculatedWidth, calculatedHeight)
        doodleNode.container.size = calculatedSize
        doodleNode.view.size = size
        return calculatedSize
    }
    fun applyPaddingVertical(doodleNode: DoodleNode, height: Double): Double{
        val calculatedHeight = height + doodleNode.padding.vertical
        doodleNode.container.height = calculatedHeight
        doodleNode.view.height = height
        return calculatedHeight
    }
    fun applyPaddingHorizontal(doodleNode: DoodleNode, width: Double): Double{
        val calculatedWidth = width + doodleNode.padding.horizontal
        doodleNode.container.width = calculatedWidth
        doodleNode.view.width = width
        return calculatedWidth
    }

}