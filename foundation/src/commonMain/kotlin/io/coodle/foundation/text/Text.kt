package io.coodle.foundation.text

import androidx.compose.runtime.Composable
import io.coodle.core.layout.Layout
import io.coodle.core.modifier.Modifier
import io.nacular.doodle.controls.text.Label
import io.nacular.doodle.drawing.Color



@Composable
fun Text(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier
){
    val label = Label().apply {
        this.text = text
        this.foregroundColor = textColor
    }
    Layout(label, modifier)
}
