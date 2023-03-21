package io.coodle.core.modifier

import androidx.compose.runtime.Stable
import io.coodle.core.layout.Alignment
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View



@Stable
interface Modifier {
    fun <R> fold(acc: R, operation: (R, Modifier) -> R): R =
        operation(acc, this)

    fun apply(
        view: View,
        doodleNode: DoodleNode,
        parent: PositionableContainer?,
        container: View
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

