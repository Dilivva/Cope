package io.cope.core.layout

import io.cope.core.node.DoodleNode
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