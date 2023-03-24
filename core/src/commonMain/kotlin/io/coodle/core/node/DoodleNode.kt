package io.coodle.core.node

import io.coodle.core.drawing.Drawing
import io.coodle.core.modifier.*
import io.coodle.core.modifier.FillMaxHeight.containAnyAlignment
import io.coodle.core.modifier.FillMaxHeight.toList
import io.coodle.core.state.BackgroundColorState
import io.nacular.doodle.controls.panels.ScrollPanel
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.*
import io.nacular.doodle.geometry.*
import io.nacular.doodle.layout.constraints.constrain


abstract class DoodleNode {
    var x = 0.0
    var y = 0.0

    var padding: Padding = Padding(0, 0, 0, 0)

    abstract val view: View
    abstract val container: Container

    var fixedHeight = false
    var fixedWidth = false

    var fixedSize: Boolean = false
        set(value) {
            field = value
            fixedHeight = value
            fixedWidth = value
        }

    var width = 0.0
        set(value) {
            if (value != field) {
                view.width = value
                container.width = value + padding.horizontal
                field = container.width
            }
        }
    var height = 0.0
        set(value) {
            if (value != field) {
                view.height = value
                container.height = value + padding.vertical
                field = container.height
            }
        }

    var radius = 0.0

    var border: Border? = null

    var size = Size(width, height)
        set(value) {
            field = value
            height = value.height
            width = value.width
        }

    internal var modifier: Modifier = Modifier
        set(value) {
            field = value
            if (positionable != null) {
                value.fold(Unit) { _, modifier ->
                    modifier.apply(view, this, positionable, container)
                }
                applyScroll()
                //view.rerender()
            }
        }


    var backgroundColor = Color.Transparent
        get() {
            return when{
                backgroundColorState.isClicked -> field.darker()
                backgroundColorState.isHovered -> field.darker(0.1f)
                else -> field
            }
        }

    internal var backgroundColorState = BackgroundColorState()

    var scrollable = false



    var bounds = Rectangle(x, y, width, height)
    var positionable: PositionableContainer? = null

    var parentSize = Size(0.0, 0.0)

    var parent: ContainerNode? = null


    /**
     * This method is called when ever there's a redraw.
     * It does the following:
     * 1. Sets the layout container [PositionableContainer]
     * 2. reapply the Modifiers
     * 3. Applies the bounds
     *
     */
    open fun measure(x: Double, y: Double, positionable: PositionableContainer): Rectangle{
        this.positionable = positionable
        modifier = modifier

        this.x = x
        this.y = y
        applyBounds(container, modifier)
        return bounds
    }

    protected fun containerLayout(view: View) = constrain(view) {
        it.top eq padding.top
        it.left eq padding.left
        it.right eq parent.right - padding.right
        it.bottom eq parent.bottom - padding.bottom
    }

    internal var drawing: Drawing? = null

    protected fun Canvas.applyShapeAndBackground() {
        drawing?.draw(this, this@DoodleNode)
    }



    private fun applyScroll() {
        if (scrollable && container.children[0] !is ScrollPanel) {
            container.children += ScrollPanel(view).apply {
                size = view.size
            }
        }
    }

    protected fun applyBounds(container: View, modifier: Modifier) {
        bounds = if (modifier.toList().containAnyAlignment()) {
            container.bounds
        } else {
            Rectangle(x, y, container.width, container.height)
        }
    }

}
