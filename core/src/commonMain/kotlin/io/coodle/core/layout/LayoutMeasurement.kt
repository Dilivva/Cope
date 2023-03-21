package io.coodle.core.layout

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Size


interface LayoutMeasurement {

    fun layout(doodleViews: MutableList<DoodleNode>, positionableContainer: PositionableContainer, container: Container)

    fun getSize(node: DoodleNode, children: MutableList<DoodleNode>): Size

    fun debugInfo(): String
}