package io.cope.core.modifier

import io.cope.core.node.DoodleNode

class Scrollable(private val scroll: Boolean): Modifier {
    override fun apply(
        doodleNode: DoodleNode
    ) {
        doodleNode.scrollable = scroll
    }

    override fun equals(other: Any?): Boolean {
        val otherScrollable = other as Scrollable
        return this.scroll == otherScrollable.scroll
    }

    override fun hashCode(): Int {
        return scroll.hashCode()
    }
}

fun Modifier.scrollable() = then(Scrollable(true))