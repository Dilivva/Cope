package io.cope.core.content

import androidx.compose.runtime.*
import io.cope.core.components.LocalFont
import io.cope.core.components.LocalFontLoader
import io.cope.core.components.font
import io.cope.core.components.loadFonts
import io.cope.core.node.RootNode
import io.nacular.doodle.application.Application
import io.nacular.doodle.core.Display
import io.nacular.doodle.drawing.FontLoader
import io.nacular.doodle.drawing.TextMetrics
import kotlinx.coroutines.*
import org.kodein.di.DI
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.instance


private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("Error: ${throwable.message}")
}

private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default + exceptionHandler)

/**
 * Set content
 *
 * @param themesAndControl
 * @param content
 * @receiver
 */
fun setContent(
    themesAndControl: List<DI.Module>,
    content: @Composable () -> Unit
) {
    val rootNode = RootNode()
    app(themesAndControl) {
        val composition = initComposeContent(
            rootNode,
            themesAndControl,
            monotonicFrameClock,
            content
        )
        composition.setContent {
            initComponents(it, content)
        }
        rootNode.getApplication(scope, it)
    }
}


/**
 * Init Local Composition components
 * @param di: Container for Injected components
 * @param content: base Composable content
 */
@Composable
private fun initComponents(di: NoArgBindingDI<*>, content: @Composable () -> Unit){
    val textMetrics: TextMetrics = di.instance()
    val fontLoader: FontLoader = di.instance()
    val display: Display = di.instance()
    //TODO: remove
    scope.launch {
        loadFonts(fontLoader)
    }
    CompositionLocalProvider(
        LocalTextMetrics provides textMetrics,
        LocalFont provides font,
        LocalFontLoader provides fontLoader,
        LocalScreenConfiguration provides display.size
    ) {
        content()
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