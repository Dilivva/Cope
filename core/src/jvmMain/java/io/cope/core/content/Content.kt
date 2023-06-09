package io.cope.core.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.DefaultMonotonicFrameClock
import androidx.compose.runtime.MonotonicFrameClock
import io.cope.core.node.RootNode
import io.nacular.doodle.application.Application
import org.kodein.di.DI
import org.kodein.di.bindings.NoArgBindingDI

actual fun initComposeContent(
    rootNode: RootNode,
    themesAndControl: List<DI.Module>,
    monotonicFrameClock: MonotonicFrameClock,
    content: @Composable () -> Unit
): Composition {
    TODO()
}

actual fun app(themesAndControl: List<DI.Module>, block: (NoArgBindingDI<*>) -> Application){

}
actual val monotonicFrameClock: MonotonicFrameClock = DefaultMonotonicFrameClock