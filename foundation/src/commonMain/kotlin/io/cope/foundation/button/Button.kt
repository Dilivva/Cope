package io.cope.foundation.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.cope.core.layout.Layout
import io.cope.core.modifier.Modifier
import io.nacular.doodle.controls.buttons.PushButton
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.system.Cursor



//Add a default size?

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Red
){
    LaunchedEffect(Unit){
        println("Recomposed")
    }

    val button = PushButton().apply {
        this.text = text
        foregroundColor = textColor
        cursor = Cursor.Pointer
    }
    Layout(button, modifier)
}