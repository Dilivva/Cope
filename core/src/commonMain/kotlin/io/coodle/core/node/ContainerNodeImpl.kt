package io.coodle.core.node

import io.coodle.core.layout.LayoutMeasurement
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.utils.ObservableList

class ContainerNodeImpl(private val measurement: LayoutMeasurement) : ContainerNode() {


     override val view: Container = container {
        layout = Layout.simpleLayout {
            measurement.layout(doodleChildren, it, this)
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

    override fun recalculateSize() {
       size = measurement.getSize(this, doodleChildren)
    }

    override fun measure(x: Double, y: Double, positionable: PositionableContainer): Rectangle {
        this.positionable = positionable
        modifier = modifier

        this.x = x
        this.y = y
        applyBounds(container, modifier)
        return bounds
    }
}