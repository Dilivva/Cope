package io.cope.core.layout

import io.cope.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size


interface LayoutMeasurement {

    fun layout(
        doodleViews: List<DoodleNode>,
        positionableContainer: PositionableContainer,
        parent: DoodleNode
    )

    fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size

    fun debugInfo(): String

    fun alignChild(
        x: Double,
        y: Double,
        alignment: Alignment,
        positionableContainer: PositionableContainer,
        doodleNode: DoodleNode
    ): Rectangle{
        val childBounds = doodleNode.measure(x, y, positionableContainer)
        return if (doodleNode.hasFixedAlignment) childBounds
        else {
            val position = alignment.align(positionableContainer, doodleNode)
            Rectangle(position.x, position.y, doodleNode.width, doodleNode.height)
        }
    }

}