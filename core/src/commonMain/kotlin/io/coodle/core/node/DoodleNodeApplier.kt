package io.coodle.core.node

import androidx.compose.runtime.AbstractApplier

class DoodleNodeApplier(root: RootNode) : AbstractApplier<DoodleNode>(root) {

    override fun insertBottomUp(index: Int, instance: DoodleNode) {
        val containerNode = current as ContainerNode
        containerNode.insertTopDown(index, instance)
    }

    override fun insertTopDown(index: Int, instance: DoodleNode) {}

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