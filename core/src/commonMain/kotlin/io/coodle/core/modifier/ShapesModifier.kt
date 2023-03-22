package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.*
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.drawing.paint
import io.nacular.doodle.geometry.*

interface Shape{
    var size: Size
    fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?)
}
class RoundedCorner(
    private val all: Int = 0,
    private val topLeft: Int = all,
    private val topRight: Int = all,
    private val bottomLeft: Int = all,
    private val bottomRight: Int = all
): Shape{
    override var size: Size = Size.Empty

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val rect = doodleNode.view.displayRect
        if (size.area != rect.size.area) {
            size = rect.size
            val paint = doodleNode.backgroundColor.paint
            val rectangle =
                if (stroke != null)
                    doodleNode.view.displayRect.inset(stroke.thickness / 2)
                else
                    doodleNode.view.displayRect

            val path = rectangle.rounded { index: Int, _: Point ->
                return@rounded when (index) {
                    0 -> topLeft.toDouble()
                    1 -> topRight.toDouble()
                    2 -> bottomRight.toDouble()
                    else -> bottomLeft.toDouble()
                }
            }
            if (stroke != null) {
                canvas.path(path, fill = paint, stroke = stroke)
            } else {
                canvas.path(path, fill = paint)
            }
        }
    }

}

object RectangleShape: Shape{
    override var size: Size = Size.Empty
    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val rect = doodleNode.view.displayRect
        if (size.area != rect.size.area) {
            size = rect.size
            val paint = doodleNode.backgroundColor.paint
            val rectangle = if (stroke != null) rect.inset(stroke.thickness / 2) else rect
            if (stroke != null) {
                canvas.rect(
                    rectangle,
                    fill = paint,
                    stroke = stroke
                )
            } else {
                canvas.rect(
                    rectangle,
                    fill = paint
                )
            }
        }
    }
}

object CircleShape: Shape{
    override var size: Size = Size.Empty

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val rect = doodleNode.view.displayRect
        if (size.area != rect.size.area) {
            size = rect.size
            val paint = doodleNode.backgroundColor.paint
            if (stroke != null) {
                canvas.circle(
                    Circle(center = rect.center, rect.height / 2),
                    fill = paint,
                    stroke = stroke
                )
            } else {
                canvas.circle(
                    Circle(center = rect.center, rect.height / 2),
                    fill = paint
                )
            }
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
    override var size: Size = Size.Empty

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        if (size.area != doodleNode.view.displayRect.size.area) {
            val paint = doodleNode.backgroundColor.paint

            val rect = if (stroke != null) doodleNode.view.displayRect.inset(stroke.thickness / 2)
            else doodleNode.view.displayRect

            size = rect.size

            val startPosition = rect.position
            val startEdge = Point(x = startPosition.x + topLeft, startPosition.y)
            val path = path(startEdge)


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
            } else {
                canvas.path(path.close(), fill = paint)
            }
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