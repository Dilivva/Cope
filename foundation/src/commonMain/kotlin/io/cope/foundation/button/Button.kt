package io.cope.foundation.button

import androidx.compose.runtime.*
import io.cope.core.components.LocalFont
import io.cope.core.content.LocalTextMetrics
import io.cope.core.layout.Box
import io.cope.core.layout.BoxScope
import io.cope.core.layout.Layout
import io.cope.core.modifier.*
import io.nacular.doodle.controls.buttons.PushButton
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.system.Cursor
import kotlin.math.roundToInt


//Add a default size?

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Red
){

    val textMetrics = LocalTextMetrics.current
    val tempFont = LocalFont.current
    val font by remember(tempFont) {
        mutableStateOf(tempFont)
    }

    val button = remember {
        PushButton().apply {
            this.text = text
            foregroundColor = textColor
            cursor = Cursor.Pointer
        }
    }
    LaunchedEffect(font){
        button.font = font
    }

    val size = textMetrics.size(text).addPadding()

    Layout(button, Modifier.width(size.width.roundToInt()).then(modifier))
}

private fun Size.addPadding(): Size{
    return Size(width = width + 12, height = height + 8)
}

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
){
    Box(
        modifier = modifier
            .background(color = Color.Orange, RoundedCorner(15))
            .shadow(elevation = 3, shape = RoundedCorner(5))
            .clickable(onClick),

    ) {
        content()
    }
}