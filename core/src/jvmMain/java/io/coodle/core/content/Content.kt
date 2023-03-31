package io.coodle.core.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import io.coodle.core.node.RootNode
import io.nacular.doodle.application.Application
import org.kodein.di.DI
import org.kodein.di.bindings.NoArgBindingDI

actual fun initComposeContent(
    rootNode: RootNode,
    themesAndControl: List<DI.Module>,
    content: @Composable () -> Unit
): Composition {
    TODO()
}

actual fun app(themesAndControl: List<DI.Module>, block: (NoArgBindingDI<*>) -> Application){

}