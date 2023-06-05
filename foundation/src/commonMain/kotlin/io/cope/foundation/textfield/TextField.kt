package io.cope.foundation.textfield

import androidx.compose.runtime.Composable
import io.cope.core.layout.Layout
import io.cope.core.modifier.Modifier
import io.nacular.doodle.controls.text.TextField
import io.nacular.doodle.controls.text.TextInput
import io.nacular.doodle.drawing.Color


@Composable
fun TextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit
){
    val textField = TextField().apply {
        this.placeHolder = "Unspecified"
        this.foregroundColor = Color.Red
        this.textChanged += { _: TextInput, _: String, new: String ->
            onTextChanged(new)
        }
    }
    Layout(textField, modifier)
}