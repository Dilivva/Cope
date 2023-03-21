package io.coodle.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import io.coodle.core.modifier.Modifier
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Size

@Composable
fun Row(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    val measurement = RowScope()
    Layout(modifier, measurement) { measurement.content() }
}


@LayoutScopeMarker
@Immutable
class RowScope: LayoutMeasurement{

    override fun layout(
        doodleViews: MutableList<DoodleNode>,
        positionableContainer: PositionableContainer,
        container: Container
    ) {
        val y = 0.0
        var x = 0.0
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = doodleNode.measure(x, y, positionableContainer)
            x += doodleNode.width
        }
    }

    override fun getSize(node: DoodleNode, children: MutableList<DoodleNode>): Size {
        val height = if (node.fixedHeight) node.height else children.maxOf { it.height }
        val width = if (node.fixedWidth) node.width else children.sumOf { it.width }
        return Size(width, height)
    }
    override fun debugInfo(): String {
        return "Row"
    }

    @Stable
    fun Modifier.align(alignment: Alignment.Vertical): Modifier{
        return then(alignment)
    }

}