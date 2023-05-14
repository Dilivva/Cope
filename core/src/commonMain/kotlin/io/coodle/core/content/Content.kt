package io.coodle.core.content

import androidx.compose.runtime.*
import io.coodle.core.node.RootNode
import io.nacular.doodle.application.Application
import io.nacular.doodle.drawing.Font
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.drawing.TextMetrics
import kotlinx.coroutines.*
import org.kodein.di.DI
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.instance
import kotlin.time.Duration.Companion.seconds


val LocalTextMetrics = staticCompositionLocalOf<TextMetrics> { error("") }
val LocalFontLoader = staticCompositionLocalOf<FontLoader> { error("") }

var font by mutableStateOf<Font?>(null)
val LocalFont = compositionLocalOf { font }

private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("Error: ${throwable.message}")
}

private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default + exceptionHandler)

fun setContent(
    themesAndControl: List<DI.Module>,
    content: @Composable () -> Unit
) {
    val rootNode = RootNode()
    app(themesAndControl) {
        val textMetrics: TextMetrics = it.instance()
        val fontLoader: FontLoader = it.instance()
        loadFonts(fontLoader)
        val composition = initComposeContent(rootNode, themesAndControl, monotonicFrameClock, content)

        composition.setContent {
            CompositionLocalProvider(
                LocalTextMetrics provides textMetrics,
                LocalFont provides font,
                LocalFontLoader provides fontLoader
            ) {
                content()
            }
        }
        rootNode.getApplication(scope, it)
    }
}

private fun loadFonts(fontLoader: FontLoader) {
    scope.launch(Dispatchers.Default) {
        font = withTimeout(15.seconds){
            fontLoader("https://fonts.cdnfonts.com/s/14955/ProductSans-Regular.woff") {
                size = 10
                weight = 400
                families = listOf("Product Sans")
            }
        }
    }
}

expect fun initComposeContent(
    rootNode: RootNode,
    themesAndControl: List<DI.Module>,
    monotonicFrameClock: MonotonicFrameClock,
    content: @Composable () -> Unit
): Composition

expect fun app(themesAndControl: List<DI.Module>, block: (NoArgBindingDI<*>) -> Application)
expect val monotonicFrameClock: MonotonicFrameClock