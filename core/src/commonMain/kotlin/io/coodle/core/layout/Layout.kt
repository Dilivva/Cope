package io.coodle.core.layout

import androidx.compose.runtime.*
import io.coodle.core.modifier.Modifier
import io.coodle.core.node.ContainerNodeImpl
import io.coodle.core.node.DoodleNode
import io.coodle.core.node.DoodleNodeApplier
import io.coodle.core.node.ViewNode
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Size


@Composable
internal fun Layout(
    modifier: Modifier = Modifier,
    measurement: LayoutMeasurement,
    content: @Composable () -> Unit
){
    ComposeNode<ContainerNodeImpl, DoodleNodeApplier>(
        factory = { ContainerNodeImpl(measurement) },
        update = {
        set(modifier){
            this.modifier = modifier
        }
        },
        content
    )
}

@Composable
internal fun Layout(
    view: View,
    modifier: Modifier = Modifier
){
    ComposeNode<ViewNode, DoodleNodeApplier>(
        factory = { ViewNode(view) }
    ) {
        set(modifier){
            this.modifier = modifier
        }
    }
}

fun layout(block: (List<DoodleNode>, PositionableContainer) -> Size): LayoutMeasurement{
    var size = Size.Empty
    return object: LayoutMeasurement{
        override fun layout(
            doodleViews: MutableList<DoodleNode>,
            positionableContainer: PositionableContainer,
            parent: DoodleNode
        ) {
            size = block(doodleViews, positionableContainer)
        }

        override fun getSize(node: DoodleNode, children: MutableList<DoodleNode>): Size {
            return size
        }

        override fun debugInfo(): String {
            return "layout()"
        }
    }

}