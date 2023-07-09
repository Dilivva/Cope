package io.cope.foundation.text

import androidx.compose.runtime.*
import io.cope.core.components.LocalFont
import io.cope.core.components.LocalFontLoader
import io.cope.core.content.LocalTextMetrics
import io.cope.core.layout.Layout
import io.cope.core.modifier.Modifier
import io.cope.foundation.font.loadFont
import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.drawing.*
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.text.TextDecoration
import io.nacular.doodle.utils.TextAlignment

typealias FontFamily = Font
typealias TextAlign = TextAlignment


//TODO: to be moved to material design
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier
){
    BasicText(
        text = text,
        modifier = modifier,
        color = Color.Black,
        fontSize = 16,
        textAlign = TextAlign.Justify,
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
 * @param lineHeight
 * @param overflow to be implemented
 * @param maxLines feature not supported yet
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
    letterSpacing: Int = 0,
    textDecoration: TextDecoration = TextDecoration(),
    textAlign: TextAlign? = null,
    lineHeight: Float = 1f,
    overflow: TextOverflow = TextOverflow.Visible,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = TextStyle.Default
){
    val textMetrics = LocalTextMetrics.current
    val textStyle = if (style == TextStyle.Default) getDefaultStyle(fontFamily, fontWeight, fontSize, fontStyle, wrapWords, textAlign, color, textDecoration) else style

    //TODO: maxLines, overflow

    val label = remember { Label() }


    val textState = remember(text, textStyle) {
        TextState(
            StyledText(
                text = text,
                font = textStyle.fontFamily,
                foreground = textStyle.color.paint,
                decoration = textStyle.decoration
            ),
            wrapWords = textStyle.wrapWords,
            textAlign = textStyle.textAlign,
            letterSpacing = letterSpacing.toDouble(),
            lineHeight = lineHeight,
            fontFamily = textStyle.fontFamily
        )
    }
    val controller = remember(textState) {
        TextController(label, textMetrics, textState)
    }

    if (!currentComposer.inserting){
        controller.updateState(textState)
    }

    Layout(label, controller.getModifiers(modifier))

}

@Composable
private fun getDefaultStyle(
    fontFamily: FontFamily?,
    fontWeight: Int?,
    fontSize: Int?,
    fontStyle: Font.Style,
    wrapWords: Boolean,
    textAlign: TextAlign?,
    color: Color,
    textDecoration: TextDecoration
): TextStyle{
    val fontLoader = LocalFontLoader.current
    val tempFont = fontFamily ?: LocalFont.current
    var font by remember(tempFont) {
        mutableStateOf(tempFont)
    }
    LaunchedEffect(tempFont){
        font = fontLoader.loadFont(tempFont,fontWeight, fontSize, fontStyle)
    }

   return TextStyle(
        wrapWords = wrapWords,
        textAlign = textAlign ?: TextAlign.Start,
        fontFamily = font,
        color = color,
        decoration = textDecoration
    )
}






