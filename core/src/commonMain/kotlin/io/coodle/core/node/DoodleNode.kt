package io.coodle.core.node

import io.coodle.core.drawing.Drawing
import io.coodle.core.modifier.*
import io.coodle.core.drawing.BackgroundColorState
import io.nacular.doodle.controls.panels.ScrollPanel
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.drawing.*
import io.nacular.doodle.geometry.*
import io.nacular.doodle.layout.constraints.constrain


/**
 * Doodle node
 * This the base node in the composition which holds
 * the view and its attributes (Modifiers).
 * It is extended by the [ContainerNode] and every [ViewNode].
 *
 * @constructor Create empty Doodle node
 */
abstract class DoodleNode {
    var x = 0.0
    var y = 0.0

    var padding: Padding = Padding(0, 0, 0, 0)
        set(value){
            field = value
            val tempSize = size
            size = Size.Empty
            size = tempSize
        }


    /** This the Doodle [View] that gets drawn on the screen.
     * Only few modifiers are applied to this object such as [DrawingModifier],
     * [ClicksModifier].
     */
    abstract val view: View


    /** Container around the [View].
     * This basically handles sizing, positioning, and padding around
     * the child view.
     * Due to some constraint on Doodle, only a parent view can apply padding
     * effect on a child view using a layout
     *
     * You won't really have to use this object directly.
     */
    abstract val container: Container


    internal var horizontalWeight: Float = 0f
    internal var verticalWeight: Float = 0f

    var fixedHeight = false
    var fixedWidth = false

    var minWidth = 0.0
        set(value) {
            field = value
            width = value
        }
    var minHeight = 0.0
        set(value) {
            field = value
            height = value
        }

    var fixedSize: Boolean = false
        set(value) {
            field = value
            fixedHeight = value
            fixedWidth = value
        }

    var width = 0.0
        set(value) {
            setWidthOrHeight(value, field, false)
            field = container.width
        }

    var height = 0.0
        set(value) {
            setWidthOrHeight(value, field, true)
            field = container.height
        }

    var radius = 0.0
    var border: Border? = null

    var size = Size.Empty
        set(value) {
            field = value
            height = value.height
            width = value.width
        }
        get() = Size(width, height)

    /** Each composable Modifier
     *  It only applies the modification to the view
     *  if there's a size that the Size Modifiers can use
     *
     *  Note: the setValue is called many times by the Composition
     */
     internal var modifier: Modifier = Modifier
        set(value) {
            field = value
            if (positionable != null) {
                value.fold(Unit) { _, modifier ->
                    modifier.apply( this)
                }
                //applyScroll()
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
    val maxSize: Size
        get() {
            return if (positionable != null){
                Size(positionable!!.width, positionable!!.height)
            }else{
                Size(0.0, 0.0)
            }
        }

    var scrollable = false



    protected var bounds = Rectangle(x, y, width, height)
    var positionable: PositionableContainer? = null

    /**
     * Called when ever there's a need to re-layout
     * 1. Sets the layout container [PositionableContainer] size
     * 2. apply the Modifiers: measuring
     * 3. apply placement
     */
    open fun measure(x: Double, y: Double, positionable: PositionableContainer): Rectangle{
        this.positionable = positionable
        modifier = modifier

        this.x = x
        this.y = y
        applyBounds(container, modifier)
        return bounds
    }


    /**
     * Calculates Width or Height
     * Pads outward (Margin) if the following conditions are true:
     * 1. Width or Height minus padding greater than minHeight or minWidth
     * 2. MinHeight or MinWidth plus padding is greater than MaxSize
     * else it pads inward
     * padding has to be set before the size
     * (To be re-written)
     * @param value
     * @param field
     * @param isHeight
     */
    @Suppress("Duplicates")
    private fun setWidthOrHeight(value: Double, field: Double, isHeight: Boolean) {
        when{
            isHeight && value != field ->{
                val withoutPadding = value - padding.vertical
                if (withoutPadding > minHeight) {
                    view.height = value - padding.vertical
                    container.height = value
                } else {
                    view.height = minHeight
                    val minHeightAndPadding = minHeight + padding.vertical
                    container.height = if (maxSize.height > minHeightAndPadding) minHeightAndPadding else minHeight
                }
            }
            !isHeight && value != field ->{
                val withoutPadding = value - padding.horizontal
                if (withoutPadding > minWidth) {
                    view.width = value - padding.horizontal
                    container.width = value
                } else {
                    view.width = minWidth
                    val minWidthAndPadding = minWidth + padding.horizontal
                    container.width = if (maxSize.width > minWidthAndPadding) minWidthAndPadding else minWidth
                }
            }
        }

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
        bounds = if (modifier.containAnyAlignment()) {
            container.bounds
        } else {
            Rectangle(x, y, container.width, container.height)
        }
    }
    private fun Double.isPositive(): Boolean{
        return this >= 0
    }
}
