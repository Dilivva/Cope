package io.coodle.foundation.text

import androidx.compose.runtime.Composable
import io.coodle.core.layout.Layout
import io.coodle.core.modifier.Modifier
import io.nacular.doodle.controls.text.Label


@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier
){

    val label = Label().apply {
        this.text = text
        //this.foregroundColor = textColor
        fitText = setOf()
    }

    Layout(label, modifier)
}
