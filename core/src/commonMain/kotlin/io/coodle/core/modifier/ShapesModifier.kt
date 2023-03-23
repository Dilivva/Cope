package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.*
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.drawing.paint
import io.nacular.doodle.geometry.*

interface Shape {
    var size: Size
    fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?)
}

class RoundedCorner(
    private val all: Int = 0,
    private val topLeft: Int = all,
    private val topRight: Int = all,
    private val bottomLeft: Int = all,
    private val bottomRight: Int = all
) : Shape {
    override var size: Size = Size.Empty
    private var cachePath: Path? = path("")

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val rect = doodleNode.view.bounds
        val paint = doodleNode.backgroundColor.paint
        val rectangle =
            if (stroke != null)
                rect.inset(stroke.thickness / 2)
            else
                rect

        val path = getPath(rectangle)

        if (stroke != null) {
            canvas.path(path, fill = paint, stroke = stroke)
        } else {
            canvas.path(path, fill = paint)
        }
    }

    private fun getPath(rect: Rectangle): Path {
        return if (rect.size != size) {
            size = rect.size
            val path = rect.rounded { index: Int, _: Point ->
                return@rounded when (index) {
                    0 -> topLeft.toDouble()
                    1 -> topRight.toDouble()
                    2 -> bottomRight.toDouble()
                    else -> bottomLeft.toDouble()
                }
            }
            cachePath = path
            cachePath!!
        } else {
            cachePath!!
        }
    }

}

object RectangleShape : Shape {
    override var size: Size = Size.Empty
    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val rect = doodleNode.view.bounds
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

object CircleShape : Shape {
    override var size: Size = Size.Empty

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val rect = doodleNode.view.bounds

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

class CutCornerShape(
    private val all: Int = 0,
    private val topLeft: Int = all,
    private val topRight: Int = all,
    private val bottomLeft: Int = all,
    private val bottomRight: Int = all
) : Shape {
    override var size: Size = Size.Empty
    private var cachePath: Path? = path("")

    override fun render(canvas: Canvas, doodleNode: DoodleNode, stroke: Stroke?) {
        val paint = doodleNode.backgroundColor.paint
        val rect = if (stroke != null)
            doodleNode.view.bounds.inset(stroke.thickness / 2)
        else
            doodleNode.view.bounds

        val path = getPath(rect)

        if (stroke != null) {
            canvas.path(path, fill = paint, stroke = stroke)
        } else {
            canvas.path(path, fill = paint)
        }
    }

    private fun getPath(rect: Rectangle): Path {
        return if (size != rect.size) {
            size = rect.size

            val startPosition = rect.position
            val startEdge = Point(x = startPosition.x + topLeft, startPosition.y)

            val path = path(startEdge)

            path.lineTo(Point(x = rect.width - topRight, startPosition.y))

            path.lineTo(Point(x = rect.right, startPosition.y + topRight))
            path.lineTo(Point(x = rect.right, rect.bottom - bottomRight))
            path.lineTo(Point(x = rect.right - bottomRight, rect.bottom))
            path.lineTo(Point(x = startPosition.x + bottomLeft, rect.bottom))
            path.lineTo(Point(x = startPosition.x, rect.bottom - bottomLeft))
            path.lineTo(Point(x = startPosition.x, startPosition.y + topLeft))
            path.lineTo(startEdge)

            cachePath = path.close()
            cachePath!!
        } else {
            cachePath!!
        }
    }

}

fun Modifier.clip(shape: Shape): Modifier {
    val clippedShape = object : Modifier {

        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.shape = shape
        }
    }
    return then(clippedShape)
}