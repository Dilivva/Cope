package io.cope.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import io.cope.core.modifier.Modifier
import io.cope.core.modifier.WeightModifier
import io.cope.core.node.DoodleNode
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.geometry.Size

@LayoutScopeMarker
@Stable
interface RowScope{

    @Stable
    fun Modifier.align(alignment: io.cope.core.layout.Alignment.Vertical): Modifier

    @Stable
    fun Modifier.weight(weight: Float): Modifier
}
@Composable
fun Row(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    val measurement = RowScopeInstance()
    Layout(modifier, measurement, content = {measurement.content()})
}



internal class RowScopeInstance: LayoutMeasurement, RowScope{
    private val weightsImpl  =  WeightsImpl()

    override fun layout(
        doodleViews: List<DoodleNode>,
        positionableContainer: PositionableContainer,
        parent: DoodleNode
    ) {
        val y = 0.0
        var x = 0.0
        doodleViews.forEachIndexed { index, doodleNode ->
            positionableContainer.children[index].bounds = doodleNode.measure(x, y, positionableContainer)
            x += doodleNode.width
        }
        weightsImpl.setWeightedChildrenAndWeights(
            doodleViews.map { it.horizontalWeight }.sum(),
            x
        )
        weightsImpl.applyWeightsForRowOrColumn(doodleViews, parent, true)
    }
    override fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size {
        val height = if (parent.fixedHeight) parent.height else children.maxOf { it.height }
        val width = if (parent.fixedWidth) parent.width else children.sumOf { it.width }
        return Size(width, height)
    }


    override fun debugInfo(): String {
        return "Row"
    }

    @Stable
    override fun Modifier.align(alignment: io.cope.core.layout.Alignment.Vertical): Modifier{
        return then(alignment)
    }

    @Stable
    override fun Modifier.weight(weight: Float): Modifier{
        require(weight > 0f){ "Weight must be greater than zero" }
        return then(WeightModifier(width = weight))
    }

}