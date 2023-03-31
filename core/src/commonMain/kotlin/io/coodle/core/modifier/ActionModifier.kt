package io.coodle.core.modifier

import io.coodle.core.node.DoodleNode
import io.coodle.core.utils.getCurrentTimeMls
import io.nacular.doodle.core.View
import io.nacular.doodle.event.PointerEvent
import io.nacular.doodle.event.PointerListener
import io.nacular.doodle.system.Cursor


private class ClicksModifier(
    private val onClick: (() -> Unit)? = null,
    private val longClick: (() -> Unit)? = null,
    private val doubleClick: (() -> Unit)? = null
): Modifier{
    private var initialized = false
    override fun apply(doodleNode: DoodleNode) {
        if (onClick != null){
            initialize {
                onClick(doodleNode, doodleNode.view, onClick)
            }
        }
        if (longClick != null){
            initialize {
                onLongClick(doodleNode, longClick)
            }
        }
        if (doubleClick != null){
            initialize {
                onDoubleClick(doodleNode, doubleClick)
            }
        }

        initialized = true
    }

    private fun initialize(block: () -> Unit){
        if (!initialized){
            block()
        }
    }

    private fun onClick(node: DoodleNode, view: View, block: () -> Unit){
        view.pointerChanged += object: ClickModifier{
            override val doodleNode = node
            override fun actionClicked() {
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



    override fun hashCode(): Int {
        var result = onClick?.hashCode() ?: 0
        result = 31 * result + (longClick?.hashCode() ?: 0)
        result = 31 * result + (doubleClick?.hashCode() ?: 0)
        result = 31 * result + initialized.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ClicksModifier

        if (onClick != other.onClick) return false
        if (longClick != other.longClick) return false
        if (doubleClick != other.doubleClick) return false
        if (initialized != other.initialized) return false

        return true
    }
}
fun Modifier.clickable(block: () -> Unit): Modifier {
    return then(ClicksModifier(onClick = block))
}

fun Modifier.combinedClickable(
    longClick: (() -> Unit)? = null,
    doubleClick: (() -> Unit)? = null
): Modifier {
    return then(ClicksModifier(longClick = longClick, doubleClick = doubleClick))
}


private interface ClickModifier: PointerListener{
    val doodleNode: DoodleNode
    fun actionPressed(){}
    fun actionRelease(){}
    fun actionClicked(){}
    override fun pressed(event: PointerEvent) {
        if (event.consumed.not()){
            doodleNode.backgroundColorState = doodleNode.backgroundColorState.copy(isClicked = true)
            doodleNode.container.rerender()
            actionPressed()
        }
        event.consume()
    }

    override fun clicked(event: PointerEvent) {
        if (event.consumed.not()){
            actionClicked()
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