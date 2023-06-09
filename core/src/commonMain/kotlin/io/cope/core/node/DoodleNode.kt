package io.cope.core.node

import io.cope.core.drawing.Drawing
import io.cope.core.modifier.*
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
    var hasFixedAlignment = false
        private set

    var fixedSize: Boolean = false
        set(value) {
            field = value
            fixedHeight = value
            fixedWidth = value
        }

    var width = 0.0

    var height = 0.0

    var radius = 0.0
    var border: Border? = null

    var size = Size.Empty
        set(value) {
            if (value != field) {
                field = value
                height = value.height
                width = value.width
            }
        }
        get() = Size(width, height)

    /** Each composable Modifiers chained
     *  It only applies the modification to the view
     *  once the parent has given the child a constraint
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

    internal var backgroundColorState = io.cope.core.drawing.BackgroundColorState()
    var maxSize: Size = Size.Empty
        protected set

    var scrollable = false

    internal var drawing: Drawing? = null

    protected var bounds = Rectangle(x, y, width, height)
    var positionable: PositionableContainer? = null
        set(value) {
            if (value != null){
                field = value
                maxSize = value.idealSize ?: value.size
            }
        }

    /**
     * Called first time to set constraints and when ever there's a need to re-layout
     * 1. Sets the layout container [PositionableContainer] size
     * 2. apply the Modifiers: measuring
     * 3. apply placement
     */
    open fun measure(x: Double, y: Double, positionable: PositionableContainer): Rectangle{
        this.x = x
        this.y = y
        this.positionable = positionable
        modifier = modifier

        applyBounds(modifier)
        return bounds
    }




    protected fun containerLayout(view: View) = constrain(view) {
        it.top eq padding.top
        it.left eq padding.left
        it.right eq parent.right - padding.right
        it.bottom eq parent.bottom - padding.bottom
    }



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

    protected fun applyBounds(modifier: Modifier) {
        bounds = if (modifier.containAnyAlignment()) {
            hasFixedAlignment = true
            container.bounds
        } else {
            hasFixedAlignment = false
            Rectangle(x, y, container.width, container.height)
        }
    }
    private fun Double.isPositive(): Boolean{
        return this >= 0
    }
}
