package io.coodle.core.node


import io.nacular.doodle.core.View
import io.nacular.doodle.core.container

class ViewNode(
    override var view: View
): DoodleNode(){


    override var container = container {
        children += view
        render = {
            applyShapeAndBackground()
        }
        layout = containerLayout(view)
    }

}