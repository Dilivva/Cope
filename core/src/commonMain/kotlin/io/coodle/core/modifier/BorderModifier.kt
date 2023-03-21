package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.Stroke



fun interface Border{
    fun draw(canvas: Canvas, doodleNode: DoodleNode)
}
internal class BorderAndShape(
    private val shape: Shape,
    private val borderStroke: BorderStroke
): Border{
    override fun draw(canvas: Canvas, doodleNode: DoodleNode) {
        val stroke = Stroke.invoke(borderStroke.color, borderStroke.width)
        shape.render(canvas, doodleNode, stroke)
    }
}
internal class BorderImpl(
    private val borderStroke: BorderStroke
): Border{
    override fun draw(canvas: Canvas, doodleNode: DoodleNode) {
        val stroke = Stroke(borderStroke.color, borderStroke.width)
        canvas.rect(doodleNode.view.bounds.atOrigin, stroke = stroke)
    }
}

data class BorderStroke(
    val color: Color,
    val width: Double
)

fun Modifier.border(
    color: Color,
    width: Double,
    shape: Shape
): Modifier{
    val border = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.border = BorderAndShape(shape, BorderStroke(color, width))
        }
    }

    return then(border)
}
fun Modifier.border(
     color: Color,
     width: Double
): Modifier{
    val border = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.border = BorderImpl(BorderStroke(color, width))
        }
    }

    return then(border)
}