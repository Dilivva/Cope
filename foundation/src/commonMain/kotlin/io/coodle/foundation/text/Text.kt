package io.coodle.foundation.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import io.coodle.core.layout.Layout
import io.coodle.core.modifier.Modifier
import io.nacular.doodle.controls.text.Label


@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier
){
    println("Text: $text")

    val label = remember {
        Label().apply {
            this.text = text
            fitText = setOf()
        }
    }
    val controller = remember{
        TextController(label, TextState(text))
    }

    if (!currentComposer.inserting){
        controller.updateState(TextState(text))
    }

    Layout(label, modifier)
}

//Behaviour and state
internal class TextController(
    private val label: Label,
    private val state: TextState
){

    fun updateState(textState: TextState){
        label.text = textState.text
    }
}
internal data class TextState(
    val text: String
)
