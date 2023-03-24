package io.coodle.core.modifier

import io.coodle.core.drawing.Drawing
import io.coodle.core.node.DoodleNode
import io.coodle.core.state.DrawScope
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.*


fun Modifier.background(
    color: Color,
    shape: Shape = RectangleShape
): Modifier{
    val backgroundModifier = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.backgroundColor = color
            doodleNode.drawing = shape
        }
    }

    return then(backgroundModifier)
}
fun Modifier.background(
    color: Color,
    shape: Shape,
    alpha: Float
): Modifier{
    val backgroundModifier = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.backgroundColor = color.opacity(alpha)
            doodleNode.drawing = shape
        }
    }

    return then(backgroundModifier)
}

fun Modifier.clip(shape: Shape): Modifier {
    val clippedShape = object : Modifier {

        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.drawing = shape
        }
    }
    return then(clippedShape)
}

fun Modifier.alpha(alpha: Float): Modifier{
    val alphaModifier = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.backgroundColor = doodleNode.backgroundColor.opacity(alpha)
        }
    }
    return then(alphaModifier)
}

internal class DrawingBehind(private val block: DrawScope.() -> Unit): Drawing{
    override fun draw(canvas: Canvas, doodleNode: DoodleNode) {
        DrawScope(canvas, doodleNode).block()
    }
}

fun Modifier.drawBehind(block: DrawScope.() -> Unit): Modifier{
    val drawModifier = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.drawing = DrawingBehind(block)
        }
    }

    return then(drawModifier)
}

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

fun Modifier.shadow(
    elevation: Int,
    shape: Shape = RectangleShape,
    shadowColor: Color = Color.Darkgray
): Modifier{
    val shadowModifier = object: Modifier{
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.drawing = ShadowModifier(
                elevation.toDouble(),
                shape,
                shadowColor.darker().opacity(0.4f)
            )
        }
    }

    return then(shadowModifier)
}