package io.cope.core.content

import androidx.compose.runtime.*
import io.nacular.doodle.drawing.TextMetrics
import io.nacular.doodle.geometry.Size

val LocalTextMetrics = staticCompositionLocalOf<TextMetrics> { error("") }
val LocalScreenConfiguration = staticCompositionLocalOf<Size> { Size.Empty }