package io.coodle.core.node

import io.coodle.core.layout.LayoutMeasurement
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Size
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

    private fun recalculateSize() {
       val calculatedSize = measurement.getSize(this, doodleChildren)
       size = when{
           calculatedSize.width == 0.0 && calculatedSize.height > 0 ->{
               Size(width = incomingSize.width, calculatedSize.height)
           }
           calculatedSize.height == 0.0 && calculatedSize.width > 0 ->{
               Size(calculatedSize.width, incomingSize.height)
           }
           calculatedSize == Size.Empty -> incomingSize
           else -> calculatedSize
       }
    }
}