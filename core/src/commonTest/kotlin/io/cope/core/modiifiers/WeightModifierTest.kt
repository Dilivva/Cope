package io.cope.core.modiifiers

import io.cope.core.modifier.WeightModifier
import io.cope.core.node.ViewNode
import io.nacular.doodle.core.view
import kotlin.test.Test
import kotlin.test.expect

class WeightModifierTest {


    @Test
    fun given_width_weight(){
      val weightModifier = WeightModifier(width = 2f)
      val viewNode = ViewNode(view {  })
      weightModifier.apply(viewNode)
      expect(2f){ viewNode.horizontalWeight }
    }
    @Test
    fun given_height_weight(){
        val weightModifier = WeightModifier(height = 2f)
        val viewNode = ViewNode(view {  })
        weightModifier.apply(viewNode)
        expect(2f){ viewNode.verticalWeight }
    }
}