package io.cope.core.modifier

import io.cope.core.layout.withInConstraint
import io.cope.core.node.DoodleNode
import io.nacular.doodle.geometry.Size

interface LayoutModifier: Modifier {


    /**
     * Applies vertical and horizontal padding to a doodleNoode
     * @param doodleNode [DoodleNode]
     * @param size [Size]
     * @return [Size]
     */
    fun applyPadding(doodleNode: DoodleNode, size: Size): Size{
        val calculatedHeight =  size.height + doodleNode.padding.vertical
        val calculatedWidth = size.width + doodleNode.padding.horizontal
        val calculatedSize = Size(
            withInConstraint(calculatedWidth, doodleNode.maxSize.width),
            withInConstraint(calculatedHeight, doodleNode.maxSize.height)
        )
        doodleNode.container.size = calculatedSize
        doodleNode.view.size = size
        return calculatedSize
    }
    fun applyPaddingVertical(doodleNode: DoodleNode, height: Double): Double{
        val calculatedHeight = withInConstraint(doodleNode.maxSize.width, height + doodleNode.padding.vertical)
        doodleNode.container.height = calculatedHeight
        doodleNode.view.height = height
        return calculatedHeight
    }
    fun applyPaddingHorizontal(doodleNode: DoodleNode, width: Double): Double{
        val calculatedWidth = withInConstraint(doodleNode.maxSize.width, width + doodleNode.padding.horizontal)
        doodleNode.container.width = calculatedWidth
        doodleNode.view.width = width
        return calculatedWidth
    }


    /**
     * subtracts the padding from the given size to return
     * the allowed or maximum size
     * @param doodleNode [DoodleNode]
     * @param size [Size]
     * @return [Size]
     */
    fun getSize(doodleNode: DoodleNode, size: Size): Size{
        val calculatedHeight =  size.height - doodleNode.padding.vertical
        val calculatedWidth = size.width - doodleNode.padding.horizontal
        return Size(calculatedWidth,calculatedHeight)
    }

}