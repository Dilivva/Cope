package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.coodle.core.utils.getCurrentTimeMls
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.system.Cursor

fun Modifier.clickable(block: () -> Unit): Modifier {
    val clickable = object : Modifier {
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            onClick(doodleNode.view, block)
        }
    }
    return then(clickable)
}

fun Modifier.combinedClickable(
    longClick: (() -> Unit)? = null,
    doubleClick: (() -> Unit)? = null
): Modifier {
    val combinedClick = object : Modifier {
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            doubleClick?.let {
                onDoubleClick(doodleNode.view, it)
            }
            longClick?.let {
                onLongClick(doodleNode.view, it)
            }

        }
    }
    return then(combinedClick)
}

private fun onClick(view: View, block: () -> Unit){
    view.pointerChanged += object: PointerListener{
        override fun clicked(event: PointerEvent) {
            if (!event.consumed){
                block()
            }
            event.consume()
        }
        override fun entered(event: PointerEvent) {
            if (event.consumed.not()) {
                view.cursor = Cursor.Pointer
            }
            event.consume()
        }
    }
}

private fun onDoubleClick(view: View, block: () -> Unit){
    view.pointerChanged += object: PointerListener {
        var start = 0L
        var end = 0L
        val time = 500L
        var accumulatedTime = 0L
        var clickCount = 0

        override fun pressed(event: PointerEvent) {
            if (event.consumed.not()){
                if (clickCount == 0) start = getCurrentTimeMls()
                event.consume()
            }
        }

        override fun released(event: PointerEvent) {
            if (event.consumed.not()){
                clickCount++
                if (clickCount == 2){
                    end = getCurrentTimeMls()
                    accumulatedTime = end - start
                }
                when{
                    clickCount == 2 && accumulatedTime <= time ->{
                        block()
                        clickCount = 0
                        accumulatedTime = 0
                    }
                    clickCount == 2 && accumulatedTime > time ->{
                        clickCount = 0
                        accumulatedTime = 0
                    }
                }

                event.consume()
            }
        }
        override fun entered(event: PointerEvent) {
            if (event.consumed.not()){
                view.cursor = Cursor.Pointer
            }
        }
    }
}

private fun onLongClick(view: View, block: () -> Unit){
    view.pointerChanged += object: PointerListener {
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
                    block()
                }
                event.consume()
            }
        }
        override fun entered(event: PointerEvent) {
            if (event.consumed.not()){
                view.cursor = Cursor.Pointer
            }
        }

    }
}
