package io.coodle.core.node

import io.nacular.doodle.core.View
import io.nacular.doodle.utils.ObservableList

abstract class ContainerNode : DoodleNode() {

    abstract val children: ObservableList<View>
    val doodleChildren = mutableListOf<DoodleNode>()

    fun move(from: Int, to: Int, count: Int) {
        children.move(from, to, count)
        doodleChildren.move(from, to, count)
    }

    fun remove(index: Int, count: Int) {
        children.remove(index, count)
        doodleChildren.remove(index, count)
    }

    fun insertBottomUp(index: Int, instance: DoodleNode) {
        children.add(index, instance.container)
        doodleChildren.add(index, instance)
    }

    fun clear() {
        children.clear()
        doodleChildren.clear()
    }

}
private fun <T> MutableList<T>.remove(index: Int, count: Int) {
    if (count == 1) {
        removeAt(index)
    } else {
        subList(index, index + count).clear()
    }
}

private fun <T> MutableList<T>.move(from: Int, to: Int, count: Int) {
    val dest = if (from > to) to else to - count
    if (count == 1) {
        if (from == to + 1 || from == to - 1) {
            val fromEl = get(from)
            val toEl = set(to, fromEl)
            set(from, toEl)
        } else {
            val fromEl = removeAt(from)
            add(dest, fromEl)
        }
    } else {
        val subView = subList(from, from + count)
        val subCopy = subView.toMutableList()
        subView.clear()
        addAll(dest, subCopy)
    }
}