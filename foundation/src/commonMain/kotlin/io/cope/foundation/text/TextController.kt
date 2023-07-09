package io.cope.foundation.text

import io.cope.core.modifier.*
import io.cope.core.node.DoodleNode
import io.cope.foundation.utils.hasSize
import io.cope.foundation.utils.hasWeight
import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.drawing.TextMetrics
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.utils.Dimension
import kotlin.math.roundToInt

/**
 * Text controller
 *
 * @constructor Create empty Text controller
 * @property label
 * @property textMetrics
 * @property state
 */
internal class TextController(
    private val label: Label,
    private val textMetrics: TextMetrics,
    private val state: TextState
){
    init {
        updateState(state)
    }

    /**
     * Update label's state
     * @param textState
     */
    fun updateState(textState: TextState){
        label.styledText = textState.styledText
        label.wrapsWords = textState.wrapWords
        label.textAlignment = textState.textAlign
        label.letterSpacing = textState.letterSpacing
        label.lineSpacing = textState.lineHeight

    }

    /**
     * Add some default size if none was specified. The result
     * size is the wrapped text within the maxWidth
     * @param modifier [Modifier]
     * @return [Modifier]
     */
    fun getModifiers(modifier: Modifier): Modifier {
        if (modifier.hasSize()) return modifier

        if (modifier.hasWeight()) return calculateSizeWithWeight(modifier)

        val sizeModifier = Modifier.sizeText()
        return modifier.then(sizeModifier)
    }


    /**
     * Calculate size based on weight. The text needs special
     * size calculation as it takes available width and height.
     * @param modifier [Modifier]
     * @return [Modifier]
     */
    private fun calculateSizeWithWeight(modifier: Modifier): Modifier{
        modifier.toList().forEach {
            if (it is WeightModifier){
                when{
                    it.height != null ->{
                        return modifier.then(object: LayoutModifier{
                            override fun apply(doodleNode: DoodleNode) {
                                val maxWidth = doodleNode.maxSize.width
                                doodleNode.width = applyPaddingHorizontal(doodleNode, maxWidth)
                                doodleNode.fixedSize = true
                            }
                        })
                    }
                    it.width != null ->{
                        label.fitText = setOf(Dimension.Height)
                        return modifier.height(label.size.height.roundToInt())
                    }
                }
            }
        }
        return modifier
    }


    /**
     * Apply a default size
     * @return [Modifier]
     */
    private fun Modifier.sizeText(): Modifier {
        val sizer = object: LayoutModifier {
            override fun apply(doodleNode: DoodleNode) {
                val maxWidth = doodleNode.maxSize.width
                doodleNode.size = applyPadding(doodleNode, Size(maxWidth, label.size.height))
                doodleNode.fixedSize = true
            }
        }
        return then(sizer)
    }
}
