package io.coodle.foundation.font

import io.nacular.doodle.drawing.Font
import io.nacular.doodle.drawing.FontLoader


suspend fun FontLoader.loadFont(
    font: Font?,
    fontWeight: Int? = null,
    fontSize: Int? = null,
    fontStyle: Font.Style = Font.Style.Normal
): Font?{
    if (font == null)
        return null
    return invoke(font) {
        if (fontWeight != null) weight = fontWeight
        if (fontSize != null) size = fontSize
        style = fontStyle
    }!!
}
