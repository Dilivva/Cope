package io.cope.core.drawing

import io.cope.core.node.DoodleNode
import io.nacular.doodle.drawing.Canvas

data class DrawScope(
    val canvas: Canvas,
    val doodleNode: DoodleNode
)
