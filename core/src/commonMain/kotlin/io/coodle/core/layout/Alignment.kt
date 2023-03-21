package io.coodle.core.layout

import io.coodle.core.modifier.Modifier
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.View
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Rectangle

interface Alignment: Modifier {

    fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition

    fun alignEnd(parent: PositionableContainer, doodleNode: DoodleNode): Double {
        val parentEnd = parent.width
        return parentEnd - doodleNode.width
    }

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        parent?.let {
            val alignmentPosition = align(it, doodleNode)
            container.bounds = Rectangle(alignmentPosition.x, alignmentPosition.y, container.width, container.height)
        }
    }


    companion object{
        val TopStart: Alignment = object: Alignment{
            override fun align(
                parent: PositionableContainer,
                doodleNode: DoodleNode
            ): AlignmentPosition {
                return AlignmentPosition.Default
            }


        }
        val TopEnd: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                return AlignmentPosition(alignEnd(parent, doodleNode),doodleNode.y)
            }

        }
        val TopCenter: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentXCenter = parent.width / 2
                val viewXCenter = doodleNode.width / 2
                val x = parentXCenter - viewXCenter
                val y = 0.0
                return AlignmentPosition(x, y)
            }

        }
        val BottomStart: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentBottom = parent.height
                val y = parentBottom - doodleNode.height
                val x = doodleNode.x
                return AlignmentPosition(x, y)
            }

        }
        val BottomEnd: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentBottom = parent.height
                val y = parentBottom - doodleNode.height
                val x = parent.width - doodleNode.width
                return AlignmentPosition(x, y)
            }

        }
        val BottomCenter: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentBottom = parent.height
                val parentXCenter = parent.width / 2
                val viewXCenter = doodleNode.width / 2
                val x = parentXCenter - viewXCenter
                val y = parentBottom - doodleNode.height
                return AlignmentPosition(x, y)
            }

        }
        val CenterStart: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentYCenter = parent.height / 2
                val viewYCenter = doodleNode.height / 2
                val x = 0.0
                val y = parentYCenter - viewYCenter
                return AlignmentPosition(x,y)
            }

        }
        val CenterEnd: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentYCenter = parent.height / 2
                val viewYCenter = doodleNode.height / 2
                val x = parent.width - doodleNode.width
                val y = parentYCenter - viewYCenter
                return AlignmentPosition(x,y)
            }

        }
        val Center: Alignment = object: Alignment{
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentXCenter = parent.width / 2
                val parentYCenter = parent.height / 2
                val viewXCenter = doodleNode.width / 2
                val viewYCenter = doodleNode.height / 2
                val x = parentXCenter - viewXCenter
                val y = parentYCenter - viewYCenter
                return AlignmentPosition(x,y)
            }
            override fun toString(): String {
                return "Alignment.Center"
            }
        }
    }

    //Inside a column
    interface Horizontal: Alignment{
        companion object{
            val Start: Horizontal = object: Horizontal{
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    return AlignmentPosition.Default
                }

            }
            val End: Horizontal = object: Horizontal{
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    return AlignmentPosition(alignEnd(parent, doodleNode), doodleNode.y)
                }

            }
            val CenterHorizontally: Horizontal = object: Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    val parentXCenter = parent.width / 2
                    val viewXCenter = doodleNode.width / 2
                    val x = parentXCenter - viewXCenter
                    return AlignmentPosition(x, doodleNode.y)
                }
            }
        }
    }
    //Inside a row
    interface Vertical: Alignment{
        companion object{
            
            val Top: Vertical = object: Vertical{
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    return AlignmentPosition.Default
                }
            }
            val Bottom: Vertical = object: Vertical{
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    val parentBottom = parent.height
                    val y = parentBottom - doodleNode.height
                    val x = doodleNode.x
                    return AlignmentPosition(x, y)
                }

                override fun toString(): String {
                    return "Alignment.Bottom"
                }
            }
            val CenterVertically: Vertical = object: Vertical{
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    val parentYCenter = parent.height / 2
                    val viewYCenter = doodleNode.height / 2
                    val x = doodleNode.x
                    val y = parentYCenter - viewYCenter
                    return AlignmentPosition(x, y)
                }
            }

        }
    }

}
data class AlignmentPosition(
    val x: Double,
    val y: Double
){
    companion object{
        val Default = AlignmentPosition(0.0,0.0)
    }
}
