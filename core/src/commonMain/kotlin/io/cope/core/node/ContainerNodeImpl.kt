package io.cope.core.node

import io.cope.core.layout.LayoutMeasurement
import io.cope.core.layout.orMax
import io.cope.core.modifier.LayoutModifier
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.utils.ObservableList

class ContainerNodeImpl(private val measurement: LayoutMeasurement) : ContainerNode(), LayoutModifier {

     override val view: Container = container {
        layout = Layout.simpleLayout {
            measurement.layout(doodleChildren, it, this@ContainerNodeImpl)
            recalculateSize()
        }
    }

    override val container = container {
        children += view
        render = {
            applyShapeAndBackground()
        }
        layout =  containerLayout(view)

    }
    override val children: ObservableList<View> = view.children

    private fun recalculateSize() {
        val calculatedSize = measurement.getSize(this, doodleChildren).orMax(maxSize)
        if (size != calculatedSize) {
            applyPadding(this, calculatedSize)
            size = calculatedSize
        }
    }


    /**
     * Node measures itself, sets idealSize (More like a maximum size),
     * sets its coordinates and positionable see [PositionableContainer],
     * re-triggers modifiers, calculate bounds and return the result to the
     * parent.
     *
     * @param x [Double]
     * @param y [Double]
     * @param positionable [PositionableContainer]
     * @return [Rectangle] preferred coordinates
     */
    override fun measure(x: Double, y: Double, positionable: PositionableContainer): Rectangle {
        view.idealSize = getSize(this, positionable.size)
        this.x = x
        this.y = y
        this.positionable = positionable
        modifier = modifier

        applyBounds(modifier)
        return bounds
    }
}