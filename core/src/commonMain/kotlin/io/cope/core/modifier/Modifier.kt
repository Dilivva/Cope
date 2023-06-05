package io.cope.core.modifier

import androidx.compose.runtime.Stable
import io.cope.core.node.DoodleNode


/**
 * Modifier
 *
 * @constructor Create empty Modifier
 */
@Stable
interface Modifier {
    /**
     * Fold
     *
     * @param acc
     * @param operation
     * @param R
     * @return
     * @receiver
     */
    fun <R> fold(acc: R, operation: (R, Modifier) -> R): R =
        operation(acc, this)

    /**
     * Apply
     *
     * @param doodleNode
     */
    fun apply(
        doodleNode: DoodleNode,
    ){}

    /**
     * Then
     *
     * @param modifier
     * @return
     */
    fun then(modifier: Modifier): Modifier {
        return if (modifier === this) this else CombinedModifier(this, modifier)
    }

    companion object : Modifier {
        override fun <R> fold(acc: R, operation: (R, Modifier) -> R): R = acc
    }

}
private class CombinedModifier(
    private val outer: Modifier,
    private val inner: Modifier
): Modifier{
    override fun <R> fold(acc: R, operation: (R, Modifier) -> R): R {
        return inner.fold(outer.fold(acc, operation), operation)
    }

    override fun equals(other: Any?): Boolean =
        other is CombinedModifier && outer == other.outer && inner == other.inner

    override fun hashCode(): Int = outer.hashCode() + 31 * inner.hashCode()

    override fun toString() = "[" + fold("") { acc, element ->
        if (acc.isEmpty()) element.toString() else "$acc, $element"
    } + "]"

}

