package io.cope.core.node

import androidx.compose.runtime.AbstractApplier

class DoodleNodeApplier(root: ContainerNode) : AbstractApplier<DoodleNode>(root) {

    override fun insertBottomUp(index: Int, instance: DoodleNode) {}

    override fun insertTopDown(index: Int, instance: DoodleNode) {
        val containerNode = current as ContainerNode
        containerNode.insertBottomUp(index, instance)
    }

    override fun move(from: Int, to: Int, count: Int) {
        val containerNode = current as ContainerNode
        containerNode.move(from, to, count)
    }

    override fun onClear() {
        val containerNode = current as ContainerNode
        containerNode.clear()
    }

    override fun remove(index: Int, count: Int) {
        val containerNode = current as ContainerNode
        containerNode.remove(index, count)
    }

}