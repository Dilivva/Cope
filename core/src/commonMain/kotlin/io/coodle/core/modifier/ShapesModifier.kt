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

interface Shape{
    val radius: Double
    fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?)
}
class RoundedCorner(
    private val all: Int = 0,
    private val topLeft: Int = all,
    private val topRight: Int = all,
    private val bottomLeft: Int = all,
    private val bottomRight: Int = all
): Shape{
    override val radius: Double
        get() = (topLeft + topRight + bottomLeft + bottomRight).toDouble()

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val paint = doodleNode.backgroundColor.paint
        val path = doodleNode.view.bounds.atOrigin.rounded { index: Int, _: Point ->
            return@rounded when(index){
                0 -> topLeft.toDouble()
                1 -> topRight.toDouble()
                2 -> bottomRight.toDouble()
                else -> bottomLeft.toDouble()
            }
        }
        if (stroke != null) {
            canvas.path(path, fill = paint, stroke = stroke)
        }else{
            canvas.path(path, fill = paint)
        }
    }

    override fun toString(): String {
        return "RoundedCorner(radius=$radius)"
    }
}

object RectangleShape: Shape{
    override val radius: Double = 0.0
    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val paint = doodleNode.backgroundColor.paint
        if (stroke != null) {
            canvas.rect(
                doodleNode.view.bounds.atOrigin,
                fill = paint,
                stroke = stroke
            )
        }else{
            canvas.rect(
                doodleNode.view.bounds.atOrigin,
                fill = paint
            )
        }
    }
}

object CircleShape: Shape{
    override val radius: Double
        get() = 0.0

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val paint = doodleNode.backgroundColor.paint
        if (stroke != null){
            canvas.circle(
                Circle( center = doodleNode.view.center,doodleNode.view.bounds.height / 2),
                fill = paint,
                stroke = stroke
            )
        }else{
            canvas.circle(
                Circle( center = doodleNode.view.center,doodleNode.view.bounds.height / 2),
                fill = paint
            )
        }

    }
}
class CutCornerShape(
    private val all: Int = 0,
    private val topLeft: Int = all,
    private val topRight: Int = all,
    private val bottomLeft: Int = all,
    private val bottomRight: Int = all
): Shape{
    override val radius: Double
        get() = 0.0

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val paint = doodleNode.backgroundColor.paint

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

        if (stroke != null) {
            canvas.path(path.close(), fill = paint, stroke = stroke)
        }else{
            canvas.path(path.close(), fill = paint)
        }
    }

}

fun Modifier.clip(shape: Shape): Modifier{
    val clippedShape = object: Modifier{

        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
           doodleNode.shape =  shape
        }
    }
    return then(clippedShape)
}