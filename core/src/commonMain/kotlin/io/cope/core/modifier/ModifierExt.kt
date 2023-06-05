package io.cope.core.modifier

fun Modifier.toList(): List<Modifier>{
    return fold(mutableListOf()) { list, modifier ->
        list.add(modifier)
        list
    }
}

internal fun  Modifier.containAnyAlignment(): Boolean{
    toList().forEach {
        if (it is io.cope.core.layout.Alignment){
            return true
        }
    }
    return false
}
