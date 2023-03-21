package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.*

interface Clip{
    val radius: Double
    fun getType(): ShapeType

    enum class ShapeType{
        Rectangle, Circle
    }

    companion object: Clip{
        override val radius: Double = 0.0

        override fun getType(): ShapeType = ShapeType.Rectangle

    }
}
class RoundedCorner(private val radii: Int = 0): Clip{
    override val radius: Double
        get() = radii.toDouble()
    override fun getType(): Clip.ShapeType = Clip.ShapeType.Rectangle

    override fun toString(): String {
        return "RoundedCorner(radius=$radii)"
    }
}

class CircleShape(private val radii: Int): Clip{
    override val radius: Double
        get() = radii.toDouble()
    override fun getType() = Clip.ShapeType.Circle
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