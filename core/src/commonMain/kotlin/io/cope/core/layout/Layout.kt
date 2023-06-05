package io.cope.core.layout

import androidx.compose.runtime.*
import io.cope.core.modifier.Modifier
import io.cope.core.node.ContainerNodeImpl
import io.cope.core.node.DoodleNode
import io.cope.core.node.DoodleNodeApplier
import io.cope.core.node.ViewNode
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Size


@Composable
fun Layout(
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
fun Layout(
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
            doodleViews: List<DoodleNode>,
            positionableContainer: PositionableContainer,
            parent: DoodleNode
        ) {
            size = block(doodleViews, positionableContainer)
        }

        override fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size {
            return size
        }

        override fun debugInfo(): String {
            return "layout()"
        }
    }

}