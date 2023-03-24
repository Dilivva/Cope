package io.coodle.core.state

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.drawing.Canvas

data class DrawScope(
    val canvas: Canvas,
    val doodleNode: DoodleNode
)
