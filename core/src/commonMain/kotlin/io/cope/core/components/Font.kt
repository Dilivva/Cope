package io.cope.core.components

import androidx.compose.runtime.*
import io.nacular.doodle.drawing.Font
import io.nacular.doodle.drawing.FontLoader
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

val LocalFontLoader = staticCompositionLocalOf<FontLoader> { error("") }
val LocalFont = compositionLocalOf { font }
internal var font by mutableStateOf<Font?>(null)

internal suspend fun loadFonts(fontLoader: FontLoader) {
    font = withTimeout(15.seconds){
        fontLoader("https://fonts.cdnfonts.com/s/14955/ProductSans-Regular.woff") {
            size = 10
            weight = 400
            families = listOf("Product Sans")
        }
    }
}