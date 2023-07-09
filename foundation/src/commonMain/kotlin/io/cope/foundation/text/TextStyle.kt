package io.cope.foundation.text

import io.nacular.doodle.drawing.Color
import io.nacular.doodle.text.TextDecoration

/**
 * Applies styling to the text
 *
 * @constructor Create default Text style
 * @property wrapWords text should be rendered on multiple lines if longer
 * @property textAlign alignment of the text
 * @property fontSize size of the font
 * @property color rendered text color
 * @property fontFamily font family
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