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
fun Column(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    val measurement = ColumnScopeInstance()
    Layout(modifier, measurement) { measurement.content() }
}


@Immutable
class ColumnScopeInstance: LayoutMeasurement, ColumnScope{

    override fun layout(
        doodleViews: MutableList<DoodleNode>,
        positionableContainer: PositionableContainer,
        container: Container
    ) {
        var y = 0.0
        val x = 0.0
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = doodleNode.measure(x, y, positionableContainer)
            y += doodleNode.height
        }
    }

    override fun getSize(node: DoodleNode, children: MutableList<DoodleNode>): Size {
        val height = if (node.fixedHeight) node.height else children.sumOf { it.height }
        val width = if (node.fixedWidth) node.width else children.maxOf { it.width }
        return Size(width, height)
    }

    override fun debugInfo(): String {
        return "Column"
    }

    @Stable
    override fun Modifier.align(alignment: Alignment.Horizontal): Modifier {
        return then(alignment)
    }

}

@LayoutScopeMarker
@Immutable
interface ColumnScope{
    @Stable
    fun Modifier.align(alignment: Alignment.Horizontal): Modifier
}


