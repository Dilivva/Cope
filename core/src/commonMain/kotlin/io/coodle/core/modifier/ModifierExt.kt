package io.coodle.core.modifier

import io.coodle.core.layout.Alignment

fun Modifier.toList(): List<Modifier>{
    return fold(mutableListOf()) { list, modifier ->
        list.add(modifier)
        list
    }
}

internal fun  Modifier.containAnyAlignment(): Boolean{
    toList().forEach {
        if (it is Alignment){
            return true
        }
    }
    return false
}
