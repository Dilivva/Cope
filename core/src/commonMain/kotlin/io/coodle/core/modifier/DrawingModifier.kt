package io.coodle.core.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import io.coodle.core.drawing.Drawing
import io.coodle.core.node.DoodleNode
import io.coodle.core.drawing.DrawScope
import io.nacular.doodle.drawing.*


class DrawingModifier(
    private val color: Color? = null,
    private val shape: Shape? = null,
    private val alpha: Float? = null,
    private val drawing: Drawing? = null
): Modifier{

    override fun apply(doodleNode: DoodleNode) {
        color?.let {
            doodleNode.backgroundColor = it
        }
        alpha?.let {
            doodleNode.backgroundColor = doodleNode.backgroundColor.opacity(it)
        }
        drawing?.let {
            doodleNode.drawing = drawing
        }
        shape?.let {
            doodleNode.drawing = shape
        }

    }

    override fun equals(other: Any?): Boolean {
        val otherModifier = other as DrawingModifier
        return this.color == otherModifier.color &&
                this.shape == otherModifier.shape &&
                this.alpha == otherModifier.alpha &&
                this.drawing == otherModifier.drawing
    }

    override fun hashCode(): Int {
        var result = color?.hashCode() ?: 0
        result = 31 * result + (shape?.hashCode() ?: 0)
        result = 31 * result + (alpha?.hashCode() ?: 0)
        result = 31 * result + (drawing?.hashCode() ?: 0)
        return result
    }

}

@Stable
fun Modifier.background(
    color: Color,
    shape: Shape = RectangleShape
): Modifier{
    return then(DrawingModifier(color, shape))
}
fun Modifier.background(
    color: Color,
    shape: Shape,
    alpha: Float
): Modifier{
    return then(DrawingModifier(color.opacity(alpha), shape))
}

fun Modifier.clip(shape: Shape): Modifier {
    return then(DrawingModifier(shape = shape))
}

fun Modifier.alpha(alpha: Float): Modifier{
    return then(DrawingModifier(alpha = alpha))
}

internal class DrawingBehind(private val block: DrawScope.() -> Unit): Drawing{
    override fun draw(canvas: Canvas, doodleNode: DoodleNode) {
        DrawScope(canvas, doodleNode).block()
    }
}

fun Modifier.drawBehind(block: DrawScope.() -> Unit): Modifier{
    return then(DrawingModifier(drawing = DrawingBehind(block)))
}

@Immutable
internal class ShadowModifier(
    private val elevation: Double,
    private val shape: Shape,
    private val shadowColor: Color
): Drawing{
    //Best Elevation is 3.0
    override fun draw(canvas: Canvas, doodleNode: DoodleNode) {
        canvas.outerShadow(horizontal = 0.0, vertical = elevation, blurRadius = 2.0, color = shadowColor){
            outerShadow(horizontal = 0.3, vertical = 0.0, color = Color.Transparent){
                shape.render(this, doodleNode, null)
            }
        }
    }

}

@Stable
fun Modifier.shadow(
    elevation: Int,
    shape: Shape = RectangleShape,
    shadowColor: Color = Color.Darkgray
): Modifier{

    return then(
        DrawingModifier(
            drawing = ShadowModifier(
                elevation.toDouble(),
                shape,
                shadowColor.darker().opacity(0.4f)
            )
        )
    )
}