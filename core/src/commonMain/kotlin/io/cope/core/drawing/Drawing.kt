package io.cope.core.drawing

import io.cope.core.node.DoodleNode
import io.nacular.doodle.drawing.Canvas

interface Drawing {
    fun draw(canvas: Canvas, doodleNode: DoodleNode)
}