package io.cope.foundation.text

import io.nacular.doodle.text.StyledText

/**
 * Encapsulates Basic text states
 * @constructor Create empty Text state
 * @property styledText
 * @property wrapWords
 * @property textAlign
 */
internal data class TextState(
    val styledText: StyledText,
    val wrapWords: Boolean,
    val textAlign: TextAlign,
    val letterSpacing: Double,
    val lineHeight: Float,
    val fontFamily: FontFamily?
)