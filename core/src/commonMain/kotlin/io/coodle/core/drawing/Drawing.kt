package io.coodle.core.drawing

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.drawing.Canvas

interface Drawing {
    fun draw(canvas: Canvas, doodleNode: DoodleNode)
}