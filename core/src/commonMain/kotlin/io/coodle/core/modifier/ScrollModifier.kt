package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode

class Scrollable: Modifier {

    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.scrollable = true
    }
}

fun Modifier.scrollable() = then(Scrollable())