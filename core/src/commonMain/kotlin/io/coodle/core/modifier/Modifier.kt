package io.coodle.core.modifier

import androidx.compose.runtime.Stable
import io.coodle.core.layout.Alignment
import io.coodle.core.node.DoodleNode


/**
 * Add extra attributes and features to views
 */
@Stable
interface Modifier {
    fun <R> fold(acc: R, operation: (R, Modifier) -> R): R =
        operation(acc, this)

    /**
     *
     */
    fun apply(
        doodleNode: DoodleNode,
    ){}

    fun then(modifier: Modifier): Modifier {
        return if (modifier === this) this else CombinedModifier(this, modifier)
    }

    companion object : Modifier {
        override fun <R> fold(acc: R, operation: (R, Modifier) -> R): R = acc
    }

    fun Modifier.toList(): List<Modifier>{
        return fold(mutableListOf()) { list, modifier ->
            list.add(modifier)
            list
        }
    }

    fun  List<Modifier>.containAnyAlignment(): Boolean{
        forEach {
            if (it is Alignment){
                return true
            }
        }
        return false
    }

}

private class CombinedModifier(
    private val outer: Modifier,
    private val inner: Modifier
): Modifier{
    override fun <R> fold(acc: R, operation: (R, Modifier) -> R): R {
        return inner.fold(outer.fold(acc, operation), operation)
    }
}

