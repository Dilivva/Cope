package io.coodle.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import io.coodle.core.modifier.Modifier
import io.coodle.core.modifier.WeightModifier
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Size

@LayoutScopeMarker
@Stable
interface RowScope{

    @Stable
    fun Modifier.align(alignment: Alignment.Vertical): Modifier

    @Stable
    fun Modifier.weight(weight: Float): Modifier
}
@Composable
fun Row(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    val measurement = RowScopeInstance()
    Layout(modifier, measurement, content = {measurement.content()})
}



internal class RowScopeInstance: LayoutMeasurement, RowScope{
    private var weights = 0f
    private var totalMeasured = 0.0
        set(value) {
            if (value != Double.POSITIVE_INFINITY){
                field = value
            }
        }
    override fun layout(
        doodleViews: MutableList<DoodleNode>,
        positionableContainer: PositionableContainer,
        container: Container
    ) {
        weights = doodleViews.map { it.horizontalWeight }.sum()
        val y = 0.0
        var x = 0.0
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = doodleNode.measure(x, y, positionableContainer)
            x += doodleNode.width
        }
        totalMeasured = x
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
    override fun Modifier.align(alignment: Alignment.Vertical): Modifier{
        return then(alignment)
    }

    @Stable
    override fun Modifier.weight(weight: Float): Modifier{
        return then(WeightModifier(width = weight, totalMeasured = totalMeasured, weights = weights))
    }

}