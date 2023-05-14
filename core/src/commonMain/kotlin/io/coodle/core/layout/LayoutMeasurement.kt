package io.coodle.core.layout

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Size


interface LayoutMeasurement {

    fun layout(
        doodleViews: List<DoodleNode>,
        positionableContainer: PositionableContainer,
        parent: DoodleNode
    )

    fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size

    fun debugInfo(): String

}