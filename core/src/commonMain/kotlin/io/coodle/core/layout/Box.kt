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
fun Box(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    val measurement = BoxScopeInstance()
    Layout(modifier, measurement) { measurement.content() }
}


@Immutable
internal class BoxScopeInstance: LayoutMeasurement, BoxScope{

    override fun layout(
        doodleViews: MutableList<DoodleNode>,
        positionableContainer: PositionableContainer,
        container: Container
    ) {
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = doodleNode.measure(0.0,0.0, positionableContainer)
        }
    }

    override fun getSize(node: DoodleNode, children: MutableList<DoodleNode>): Size {
        val height = if (node.fixedHeight) node.height else children.maxOf { it.height }
        val width = if (node.fixedWidth) node.width else children.maxOf { it.width }
        return Size(width, height)
    }

    @Stable
    override fun Modifier.align(alignment: Alignment): Modifier {
        return then(alignment)
    }
    override fun debugInfo(): String {
        return "Box"
    }

}

@LayoutScopeMarker
@Immutable
interface BoxScope{

    @Stable
    fun Modifier.align(alignment: Alignment): Modifier
}