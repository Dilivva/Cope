package io.coodle.core.modifier

import io.coodle.core.drawing.Drawing
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.drawing.Canvas
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.Stroke



fun interface Border: Drawing{
    override fun draw(canvas: Canvas, doodleNode: DoodleNode)
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

class BorderModifier(
    private val color: Color,
    private val width: Double,
    private val shape: Shape? = null
): Modifier{

    override fun apply(doodleNode: DoodleNode) {
        val border = if (shape != null){
            BorderAndShape(shape, BorderStroke(color, width))
        }else BorderImpl(BorderStroke(color, width))
       doodleNode.drawing = border
    }

    override fun equals(other: Any?): Boolean {
        val otherModifier = other as BorderModifier
        return this.shape == otherModifier.shape &&
                this.width == otherModifier.width &&
                this.color == otherModifier.color
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + (shape?.hashCode() ?: 0)
        return result
    }
}
fun Modifier.border(
    color: Color,
    width: Double,
    shape: Shape
): Modifier{
    return then(BorderModifier(color, width, shape))
}
fun Modifier.border(
     color: Color,
     width: Double
): Modifier{

    return then(BorderModifier(color, width))
}