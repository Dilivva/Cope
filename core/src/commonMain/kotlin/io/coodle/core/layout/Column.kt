package io.coodle.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import io.coodle.core.modifier.Modifier
import io.coodle.core.modifier.WeightModifier
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Size


@LayoutScopeMarker
@Stable
interface ColumnScope{
    @Stable
    fun Modifier.weight(weight: Float): Modifier

    @Stable
    fun Modifier.align(alignment: Alignment.Horizontal): Modifier

}


@Composable
fun Column(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    val measurement = ColumnScopeInstance()
    Layout(modifier, measurement, content = { measurement.content() })
}



@Immutable
private class ColumnScopeInstance: LayoutMeasurement, ColumnScope{
    private val weightsImpl  =  WeightsImpl()

    override fun layout(
        doodleViews: List<DoodleNode>,
        positionableContainer: PositionableContainer,
        parent: DoodleNode
    ) {
        var y = 0.0
        val x = 0.0
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = doodleNode.measure(x, y, positionableContainer)
            y += doodleNode.height
        }
        weightsImpl.setWeightedChildrenAndWeights(
            doodleViews.map { it.verticalWeight }.sum(),
            y
        )
        weightsImpl.applyWeightsForRowOrColumn(doodleViews, parent, false)
    }

    override fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size {
        val height = if (parent.fixedHeight) parent.height else children.sumOf { it.height }
        val width = if (parent.fixedWidth) parent.width else children.maxOf { it.width }
        return Size(width, height)
    }

    override fun debugInfo(): String {
        return "Column"
    }

    @Stable
    override fun Modifier.align(alignment: Alignment.Horizontal): Modifier {
        return then(alignment)
    }

    @Stable
    override fun Modifier.weight(weight: Float): Modifier{
        return then(WeightModifier(height = weight))
    }

}



