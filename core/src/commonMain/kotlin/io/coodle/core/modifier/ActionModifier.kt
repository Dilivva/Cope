package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.coodle.core.utils.getCurrentTimeMls
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.View
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.system.Cursor

fun Modifier.clickable(block: () -> Unit): Modifier {
    var initialized = false
    val clickable = object : Modifier {
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            if (!initialized) {
                initialized = true
                onClick(doodleNode, doodleNode.view, block)
            }
        }
    }
    return then(clickable)
}

fun Modifier.combinedClickable(
    longClick: (() -> Unit)? = null,
    doubleClick: (() -> Unit)? = null
): Modifier {
    var initializedDoubleClick = false
    var initializeLongClick = false
    val combinedClick = object : Modifier {
        override fun apply(
            view: View,
            doodleNode: DoodleNode,
            parent: PositionableContainer?,
            container: View
        ) {
            if (initializedDoubleClick.not() && doubleClick != null){
                initializedDoubleClick = true
                onDoubleClick(doodleNode, doubleClick)
            }
            if (initializeLongClick.not() && longClick != null){
                initializeLongClick = true
                onLongClick(doodleNode, longClick)
            }
        }
    }
    return then(combinedClick)
}

private fun onClick(node: DoodleNode, view: View, block: () -> Unit){
    view.pointerChanged += object: ClickModifier{
        override val doodleNode = node
        override fun actionRelease() {
            block()
        }
    }
}

private fun onDoubleClick(node: DoodleNode, block: () -> Unit){
    node.view.pointerChanged += object: ClickModifier {
        override val doodleNode = node

        var start = 0L
        var end = 0L
        val time = 500L
        var accumulatedTime = 0L
        var clickCount = 0

        override fun actionPressed() {
            if (clickCount == 0) start = getCurrentTimeMls()
        }

        override fun actionRelease() {
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
        }
    }
}

private fun onLongClick(node: DoodleNode, block: () -> Unit){
    node.view.pointerChanged += object: ClickModifier {
        override val doodleNode = node
        var start = 0L
        var end = 0L
        val time = 500
        override fun actionPressed() {
            start = getCurrentTimeMls()
        }

        override fun actionRelease() {
            end = getCurrentTimeMls()
            if ((end - start) > time){
                block()
            }
        }
    }
}


private interface ClickModifier: PointerListener{
    val doodleNode: DoodleNode


    fun actionPressed(){}
    fun actionRelease(){}
    override fun pressed(event: PointerEvent) {
        if (event.consumed.not()){
            doodleNode.backgroundColorState = doodleNode.backgroundColorState.copy(isClicked = true)
            doodleNode.container.rerender()
            actionPressed()
        }
        event.consume()
    }

    override fun released(event: PointerEvent) {
        if (event.consumed.not()){
            doodleNode.backgroundColorState = doodleNode.backgroundColorState.copy(isClicked = false)
            doodleNode.container.rerender()
            actionRelease()
        }
        event.consume()
    }


    override fun entered(event: PointerEvent) {
        if (event.consumed.not()) {
            doodleNode.view.cursor = Cursor.Pointer
            doodleNode.backgroundColorState = doodleNode.backgroundColorState.copy(isHovered = true)
            doodleNode.container.rerender()
        }
        event.consume()
    }

    override fun exited(event: PointerEvent) {
        if (event.consumed.not()) {
            doodleNode.backgroundColorState = doodleNode.backgroundColorState.copy(isHovered = false)
            doodleNode.container.rerender()
        }
        event.consume()
    }
}