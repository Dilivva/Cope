package io.cope.foundation.text

import androidx.compose.runtime.*
import io.cope.core.layout.Layout
import io.cope.core.modifier.Modifier
import io.cope.core.node.DoodleNode
import io.cope.foundation.font.loadFont
import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.drawing.*
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.text.TextDecoration
import io.nacular.doodle.utils.HorizontalAlignment
import io.nacular.doodle.utils.VerticalAlignment


//TODO: to be moved to material design
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier
){
    BasicText(
        text,
        modifier,
        color = Color.White,
        fontSize = 16,
        textAlign = TextAlign.Start,
        fontWeight = 400,
        fontStyle = Font.Style.Normal,
        wrapWords = true
    )
}


/**
 * Basic text
 *
 * @param text
 * @param modifier
 * @param color
 * @param fontSize
 * @param fontStyle
 * @param fontWeight
 * @param fontFamily
 * @param wrapWords
 * @param letterSpacing feature not supported yet
 * @param textDecoration
 * @param textAlign
 * @param lineHeight feature not supported yet
 * @param overflow to be implemented
 * @param maxLines feature not supported yet
 * @param minLines feature not supported yet
 * @param style
 */
@Composable
fun BasicText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontSize: Int? = null,
    fontStyle: Font.Style = Font.Style.Normal,
    fontWeight: Int? = null,
    fontFamily: FontFamily? = null,
    wrapWords: Boolean = false,
    letterSpacing: Int? = null,
    textDecoration: TextDecoration = TextDecoration(),
    textAlign: TextAlign? = null,
    lineHeight: Int? = null,
    overflow: TextOverflow = TextOverflow.Visible,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle = TextStyle.Default
){
    val textMetrics = io.cope.core.content.LocalTextMetrics.current
    val fontLoader = io.cope.core.content.LocalFontLoader.current
    val textStyle = if (style != TextStyle.Default){
        style
    }else{
        val tempFont = fontFamily ?: io.cope.core.content.LocalFont.current
        var font by remember(tempFont) {  mutableStateOf(tempFont) }
        LaunchedEffect(tempFont){
            //This recomposes the whole composable. Not cool
            font = fontLoader.loadFont(tempFont,fontWeight, fontSize, fontStyle)
        }
        TextStyle(
           wrapWords = wrapWords,
           textAlign = textAlign ?: TextAlign.Start,
            fontFamily = font,
            color = color,
            decoration = textDecoration
        )

    }
    val label = remember {
        Label().apply {
            fitText = setOf()
        }
    }

    val textState = TextState(
        StyledText(text = text, font = textStyle.fontFamily, foreground =  textStyle.color.paint, decoration = textStyle.decoration),
        wrapWords = textStyle.wrapWords,
        textAlign = textStyle.textAlign
    )
    val controller = remember {  TextController(label, textMetrics, textState) }


    if (!currentComposer.inserting){
        controller.updateState(textState)
    }

    Layout(label, controller.getModifiers(modifier))

}

typealias FontFamily = Font

/**
 * Text align
 *
 * @constructor Create empty Text align
 * @property horizontalAlignment
 * @property verticalAlignment
 */
data class TextAlign(
    val horizontalAlignment: HorizontalAlignment,
    val verticalAlignment: VerticalAlignment,
){
    companion object{
        val Start = TextAlign(horizontalAlignment = HorizontalAlignment.Left, verticalAlignment = VerticalAlignment.Top)
        val Center = TextAlign(horizontalAlignment = HorizontalAlignment.Center, verticalAlignment = VerticalAlignment.Middle)
    }
}

/**
 * Text style
 *
 * @constructor Create empty Text style
 * @property wrapWords
 * @property textAlign
 * @property fontSize
 * @property color
 * @property fontFamily
 * @property fontWeight
 * @property decoration
 */
data class TextStyle(
    val wrapWords: Boolean = true,
    val textAlign: TextAlign = TextAlign.Start,
    val fontSize: Int = 18,
    val color: Color = Color.Black,
    val fontFamily: FontFamily? = null,
    val fontWeight: Int = 400,
    val decoration: TextDecoration = TextDecoration()
){
    companion object{
        val Default = TextStyle()
    }
}

/**
 * Text controller
 *
 * @constructor Create empty Text controller
 * @property label
 * @property textMetrics
 * @property state
 */
private class TextController(
    private val label: Label,
    private val textMetrics: TextMetrics,
    private val state: TextState
){
    init {
        updateState(state)
    }

    /**
     * Update label's internal state
     * @param textState
     */
    fun updateState(textState: TextState){
        label.styledText = textState.styledText
        label.wrapsWords = textState.wrapWords
        label.horizontalAlignment = textState.textAlign.horizontalAlignment
        label.verticalAlignment = textState.textAlign.verticalAlignment
    }

    /**
     * Add some default size if none was specified
     * @param modifier
     * @return
     */
    fun getModifiers(modifier: Modifier): Modifier{
        val sizeModifier = Modifier.sizeText(textMetrics)
        return sizeModifier.then(modifier)
    }
    private fun Modifier.sizeText(textMetrics: TextMetrics):Modifier{
        val sizer = object: Modifier{
            override fun apply(doodleNode: DoodleNode) {
                val size = textMetrics.size(state.styledText, width = doodleNode.maxSize.width)
                doodleNode.minHeight = size.height
                doodleNode.minWidth = size.width
            }
        }
        return then(sizer)
    }
}

