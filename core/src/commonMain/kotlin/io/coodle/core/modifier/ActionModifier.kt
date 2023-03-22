package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.coodle.core.utils.getCurrentTimeMls
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.event.PointerListener.Companion.clicked

fun Modifier.clickable(block: () -> Unit): Modifier {
    val clickable = object : Modifier {
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doodleNode.view.pointerChanged += clicked { event ->
                if (!event.consumed){
                    block()
                }
                event.consume()
            }
        }
    }
    return then(clickable)
}

fun Modifier.combinedClickable(
    longClick: () -> Unit = {},
    doubleClick: () -> Unit = {}
): Modifier {
    val combinedClick = object : Modifier {
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {

            doodleNode.view.pointerChanged += object: PointerListener {
                var start = 0L
                var end = 0L
                var time = 200
                var clickCount = 0

                override fun pressed(event: PointerEvent) {
                    if (event.consumed.not()){
                        start = getCurrentTimeMls()
                        event.consume()
                    }
                }

                override fun released(event: PointerEvent) {
                    if (event.consumed.not()){
                        end = getCurrentTimeMls()
                        if (clickCount == 2){
                            doubleClick()
                            clickCount = 0
                        }else{
                            if ((end - start) > time){
                                clickCount = 0
                            }else {
                                clickCount++
                            }
                        }

                        event.consume()
                    }

                }
            }
            doodleNode.view.pointerChanged += object: PointerListener {
                var start = 0L
                var end = 0L
                val time = 500
                override fun pressed(event: PointerEvent) {
                    if (!event.consumed){
                        start = getCurrentTimeMls()
                        event.consume()
                    }
                }


                override fun released(event: PointerEvent) {
                    if (!event.consumed){
                        end = getCurrentTimeMls()
                        if ((end - start) > time){
                            longClick()
                        }
                        event.consume()
                    }
                }

            }
        }
    }
    return then(combinedClick)
}

