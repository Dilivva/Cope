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
            doodleNode.container.bounds = Rectangle(alignmentPosition.x, alignmentPosition.y, doodleNode.width, doodleNode.height)
        }
    }


    companion object{
        val TopStart: Alignment = object: Alignment {
            override fun align(
                parent: PositionableContainer,
                doodleNode: DoodleNode
            ): AlignmentPosition {
                return AlignmentPosition(doodleNode.x, doodleNode.y)
            }

        }
        val TopEnd: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                return AlignmentPosition(alignEnd(parent, doodleNode), doodleNode.y)
            }

        }
        val TopCenter: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val x = (parent.width - doodleNode.width) / 2
                val y = 0.0
                return AlignmentPosition(x, y)
            }

        }
        val BottomStart: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentBottom = parent.height
                val y = parentBottom - doodleNode.height
                val x = doodleNode.x
                return AlignmentPosition(x, y)
            }

        }
        val BottomEnd: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentBottom = parent.height
                val y = parentBottom - doodleNode.height
                val x = parent.width - doodleNode.width
                return AlignmentPosition(x, y)
            }

        }
        val BottomCenter: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val parentBottom = parent.height
                val x = (parent.width - doodleNode.width) / 2
                val y = parentBottom - doodleNode.height
                return AlignmentPosition(x, y)
            }

        }
        val CenterStart: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val x = 0.0
                val y = (parent.height - doodleNode.height) / 2
                return AlignmentPosition(x, y)
            }

        }
        val CenterEnd: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val x = parent.width - doodleNode.width
                val y = (parent.height - doodleNode.height) / 2
                return AlignmentPosition(x, y)
            }

        }
        val Center: Alignment = object: Alignment {
            override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                val x = (parent.width - doodleNode.width) / 2
                val y = (parent.height - doodleNode.height) / 2
                return AlignmentPosition(x, y)
            }
            override fun toString(): String {
                return "Alignment.Center"
            }
        }
    }

    //Inside a column
    interface Horizontal: Alignment {
        companion object{
            val Start: Horizontal = object: Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    return AlignmentPosition(doodleNode.x, doodleNode.y)
                }

            }
            val End: Horizontal = object: Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    return AlignmentPosition(alignEnd(parent, doodleNode), doodleNode.y)
                }

            }
            val CenterHorizontally: Horizontal = object: Horizontal {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    val x = (parent.width - doodleNode.width) / 2
                    return AlignmentPosition(x, doodleNode.y)
                }
            }
        }
    }
    //Inside a row
    interface Vertical: Alignment {
        companion object{
            
            val Top: Vertical = object: Vertical {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {

                    return AlignmentPosition(doodleNode.x, doodleNode.y)
                }
            }
            val Bottom: Vertical = object: Vertical {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    val y = parent.height - doodleNode.height
                    val x = doodleNode.x
                    return AlignmentPosition(x, y)
                }

                override fun toString(): String {
                    return "Alignment.Bottom"
                }

            }
            val CenterVertically: Vertical = object: Vertical {
                override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
                    val x = doodleNode.x
                    val y = (parent.height - doodleNode.height) / 2
                    return AlignmentPosition(x, y)
                }
            }

        }
    }

}

internal class AlignmentImpl: Alignment{
    override fun align(parent: PositionableContainer, doodleNode: DoodleNode): AlignmentPosition {
        TODO("Not yet implemented")
    }

}

data class AlignmentPosition(
    val x: Double,
    val y: Double
)
