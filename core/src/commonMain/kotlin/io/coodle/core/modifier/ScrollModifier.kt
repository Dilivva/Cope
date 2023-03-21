package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View

class Scrollable: Modifier {

    override fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
    ) {
        doodleNode.scrollable = true
    }
}

fun Modifier.scrollable() = then(Scrollable())