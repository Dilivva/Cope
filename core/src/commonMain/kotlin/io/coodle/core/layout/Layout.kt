package io.coodle.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReusableComposeNode
import io.coodle.core.modifier.Modifier
import io.coodle.core.node.ContainerNodeImpl
import io.coodle.core.node.DoodleNodeApplier
import io.coodle.core.node.ViewNode
import io.nacular.doodle.core.*



@Composable
fun Layout(modifier: Modifier = Modifier, measurement: LayoutMeasurement, content: @Composable () -> Unit){
    ReusableComposeNode<ContainerNodeImpl, DoodleNodeApplier>(
        factory = { ContainerNodeImpl(measurement) },
        {
        set(modifier){
            this.modifier = modifier
        }
        },
        content
    )
}

@Composable
fun Layout(view: View, modifier: Modifier = Modifier){
    ReusableComposeNode<ViewNode, DoodleNodeApplier>(
        factory = { ViewNode(view) }
    ) {
        set(view){
            update(view)
        }
        set(modifier){
            this.modifier = modifier
        }
    }
}