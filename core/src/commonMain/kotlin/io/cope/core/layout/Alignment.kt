package io.cope.core.layout

import io.cope.core.modifier.Modifier
import io.cope.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Rectangle

interface Alignment: Modifier {

    fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition

    fun alignEnd(parent: PositionableContainer, doodleNode: DoodleNode): Double {
        val parentEnd = parent.width
        return parentEnd - doodleNode.width
    }

    override fun apply(
        doodleNode: DoodleNode,
    ) {
        doodleNode.positionable?.let {
            val alignmentPosition = align(it, doodleNode)
            doodleNode.container.bounds = Rectangle(alignmentPosition.x, alignmentPosition.y, doodleNode.container.width, doodleNode.container.height)
        }
    }


    companion object{
        val TopStart: Alignment = object: Alignment {
            override fun align(
                parent: PositionableContainer,
                doodleNode: DoodleNode
            ): io.cope.core.layout.AlignmentPosition {
                return io.cope.core.layout.AlignmentPosition.Companion.Default
            }

        }
        val TopEnd: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                return io.cope.core.layout.AlignmentPosition(alignEnd(parent, doodleNode), doodleNode.y)
            }

        }
        val TopCenter: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentXCenter = parent.width / 2
                val viewXCenter = doodleNode.width / 2
                val x = parentXCenter - viewXCenter
                val y = 0.0
                return io.cope.core.layout.AlignmentPosition(x, y)
            }

        }
        val BottomStart: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentBottom = parent.height
                val y = parentBottom - doodleNode.height
                val x = doodleNode.x
                return io.cope.core.layout.AlignmentPosition(x, y)
            }

        }
        val BottomEnd: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentBottom = parent.height
                val y = parentBottom - doodleNode.height
                val x = parent.width - doodleNode.width
                return io.cope.core.layout.AlignmentPosition(x, y)
            }

        }
        val BottomCenter: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentBottom = parent.height
                val parentXCenter = parent.width / 2
                val viewXCenter = doodleNode.width / 2
                val x = parentXCenter - viewXCenter
                val y = parentBottom - doodleNode.height
                return io.cope.core.layout.AlignmentPosition(x, y)
            }

        }
        val CenterStart: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentYCenter = parent.height / 2
                val viewYCenter = doodleNode.height / 2
                val x = 0.0
                val y = parentYCenter - viewYCenter
                return io.cope.core.layout.AlignmentPosition(x, y)
            }

        }
        val CenterEnd: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentYCenter = parent.height / 2
                val viewYCenter = doodleNode.height / 2
                val x = parent.width - doodleNode.width
                val y = parentYCenter - viewYCenter
                return io.cope.core.layout.AlignmentPosition(x, y)
            }

        }
        val Center: io.cope.core.layout.Alignment = object: io.cope.core.layout.Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                val parentXCenter = parent.width / 2
                val parentYCenter = parent.height / 2
                val viewXCenter = doodleNode.width / 2
                val viewYCenter = doodleNode.height / 2
                val x = parentXCenter - viewXCenter
                val y = parentYCenter - viewYCenter
                return io.cope.core.layout.AlignmentPosition(x, y)
            }
            override fun toString(): String {
                return "Alignment.Center"
            }
        }
    }

    //Inside a column
    interface Horizontal: io.cope.core.layout.Alignment {
        companion object{
            val Start: io.cope.core.layout.Alignment.Horizontal = object: io.cope.core.layout.Alignment.Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                    return io.cope.core.layout.AlignmentPosition.Companion.Default
                }

            }
            val End: io.cope.core.layout.Alignment.Horizontal = object: io.cope.core.layout.Alignment.Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                    return io.cope.core.layout.AlignmentPosition(alignEnd(parent, doodleNode), doodleNode.y)
                }

            }
            val CenterHorizontally: io.cope.core.layout.Alignment.Horizontal = object:
                io.cope.core.layout.Alignment.Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                    val parentXCenter = parent.width / 2
                    val viewXCenter = doodleNode.width / 2
                    val x = parentXCenter - viewXCenter
                    return io.cope.core.layout.AlignmentPosition(x, doodleNode.y)
                }
            }
        }
    }
    //Inside a row
    interface Vertical: io.cope.core.layout.Alignment {
        companion object{
            
            val Top: io.cope.core.layout.Alignment.Vertical = object: io.cope.core.layout.Alignment.Vertical {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                    return io.cope.core.layout.AlignmentPosition.Companion.Default
                }
            }
            val Bottom: io.cope.core.layout.Alignment.Vertical = object: io.cope.core.layout.Alignment.Vertical {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                    val parentBottom = parent.height
                    val y = parentBottom - doodleNode.height
                    val x = doodleNode.x
                    return io.cope.core.layout.AlignmentPosition(x, y)
                }

                override fun toString(): String {
                    return "Alignment.Bottom"
                }
            }
            val CenterVertically: io.cope.core.layout.Alignment.Vertical = object:
                io.cope.core.layout.Alignment.Vertical {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): io.cope.core.layout.AlignmentPosition {
                    val parentYCenter = parent.height / 2
                    val viewYCenter = doodleNode.height / 2
                    val x = doodleNode.x
                    val y = parentYCenter - viewYCenter
                    return io.cope.core.layout.AlignmentPosition(x, y)
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
        val Default = io.cope.core.layout.AlignmentPosition(0.0, 0.0)
    }
}
