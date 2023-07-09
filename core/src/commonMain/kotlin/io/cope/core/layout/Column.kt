package io.cope.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import io.cope.core.modifier.Modifier
import io.cope.core.modifier.WeightModifier
import io.cope.core.node.DoodleNode
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
fun Column(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Horizontal.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val measurement = ColumnScopeInstance(horizontalAlignment = horizontalAlignment)
    Layout(modifier, measurement, content = { measurement.content() })
}



@Immutable
private class ColumnScopeInstance(
    private val horizontalAlignment: Alignment.Horizontal
): LayoutMeasurement, ColumnScope{
    private val weightsImpl  =  WeightsImpl()

    override fun layout(
        doodleViews: List<DoodleNode>,
        positionableContainer: PositionableContainer,
        parent: DoodleNode
    ) {
        var y = 0.0
        val x = 0.0
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = alignChild(x, y, horizontalAlignment, positionableContainer, doodleNode)
            y += doodleNode.height
        }
        weightsImpl.setWeightedChildrenAndWeights(doodleViews.map { it.verticalWeight }.sum(), y)
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
        require(weight > 0f){ "Weight must be greater than zero" }
        return then(WeightModifier(height = weight))
    }

}



