package io.coodle.foundation.button

import androidx.compose.runtime.Composable
import io.coodle.core.layout.Layout
import io.coodle.core.modifier.Modifier
import io.nacular.doodle.controls.buttons.PushButton
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.system.Cursor



//Add a default size?

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color
){
    val button = PushButton().apply {
        this.text = text
        foregroundColor = textColor
        cursor = Cursor.Pointer
    }
    Layout(button, modifier)
}