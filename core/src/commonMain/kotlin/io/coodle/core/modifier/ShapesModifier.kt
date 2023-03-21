package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.*
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.drawing.paint
import io.nacular.doodle.geometry.Circle
import io.nacular.doodle.geometry.Point
import io.nacular.doodle.geometry.path
import io.nacular.doodle.geometry.rounded

interface Clip{
    val radius: Double
    fun render(canvas: Canvas, doodleNode: DoodleNode)
}
class RoundedCorner(
    private val topLeft: Int = 0,
    private val topRight: Int = 0,
    private val bottomLeft: Int = 0,
    private val bottomRight: Int = 0
): Clip{
    override val radius: Double
        get() = (topLeft + topRight + bottomLeft + bottomRight).toDouble()

    override fun render(canvas: Canvas, doodleNode: DoodleNode) {
        val paint = doodleNode.backgroundColor.paint
        val stroke = Stroke.invoke(doodleNode.border.color, doodleNode.border.width.toDouble())
        val path = doodleNode.view.bounds.atOrigin.rounded { index: Int, _: Point ->
            return@rounded when(index){
                0 -> topLeft.toDouble()
                1 -> topRight.toDouble()
                2 -> bottomRight.toDouble()
                else -> bottomLeft.toDouble()
            }
        }
        canvas.path(path, fill = paint, stroke = stroke)
    }

    override fun toString(): String {
        return "RoundedCorner(radius=$radius)"
    }
}

object RectangleShape: Clip{
    override val radius: Double = 0.0
    override fun render(canvas: Canvas, doodleNode: DoodleNode) {
        val paint = doodleNode.backgroundColor.paint
        val stroke = Stroke.invoke(doodleNode.border.color, doodleNode.border.width.toDouble())
        canvas.rect(
            doodleNode.view.bounds.atOrigin,
            fill = paint,
            stroke = stroke
        )
    }
}

object CircleShape: Clip{
    override val radius: Double
        get() = 0.0

    override fun render(canvas: Canvas, doodleNode: DoodleNode) {
        val paint = doodleNode.backgroundColor.paint
        val stroke = Stroke.invoke(doodleNode.border.color, doodleNode.border.width.toDouble())
        canvas.circle(
            Circle( center = doodleNode.view.center,doodleNode.view.bounds.height / 2),
            fill = paint,
            stroke = stroke
        )
    }
}
class CutCornerShape(
    private val topLeft: Int = 0,
    private val topRight: Int = 0,
    private val bottomLeft: Int = 0,
    private val bottomRight: Int = 0
): Clip{
    override val radius: Double
        get() = 0.0

    override fun render(canvas: Canvas, doodleNode: DoodleNode) {
        val paint = doodleNode.backgroundColor.paint
        val stroke = Stroke.invoke(doodleNode.border.color, doodleNode.border.width.toDouble())

        val startPosition = doodleNode.view.bounds.atOrigin.position
        val startEdge = Point(x = startPosition.x + topLeft, startPosition.y )
        val path = path(startEdge)
        val rect = doodleNode.view.bounds.atOrigin

        path.lineTo(Point(x = rect.width - topRight, startPosition.y))
        path.lineTo(Point(x = rect.width, startPosition.y + topRight))
        path.lineTo(Point(x = rect.width, rect.height - bottomRight))
        path.lineTo(Point(x = rect.width - bottomRight, rect.height))
        path.lineTo(Point(x = startPosition.x + bottomLeft, rect.height))
        path.lineTo(Point(x = startPosition.x, rect.height - bottomLeft))
        path.lineTo(Point(x = startPosition.x, startPosition.y + topLeft))
        path.lineTo(startEdge)
        canvas.path(path.close(), fill = paint, stroke = stroke)
    }

}

fun Modifier.clip(clip: Clip): Modifier{
    val clippedShape = object: Modifier{

        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
           doodleNode.shapeType =  clip
        }
    }
    return then(clippedShape)
}