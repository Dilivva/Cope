package io.coodle.foundation.textfield

import androidx.compose.runtime.Composable
import io.coodle.core.layout.Layout
import io.coodle.core.modifier.Modifier
import io.nacular.doodle.controls.text.TextField
import io.nacular.doodle.controls.text.TextInput
import io.nacular.doodle.drawing.Color


@Composable
fun TextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit
){
    val textField = TextField().apply {
        this.placeHolder = "Email"
        this.foregroundColor = Color.Red
        this.textChanged += { _: TextInput, _: String, new: String ->
            onTextChanged(new)
        }
    }
    Layout(textField, modifier)
}