package io.coodle.core.content

import androidx.compose.runtime.*
import io.coodle.core.node.DoodleNodeApplier
import io.coodle.core.node.RootNode
import io.nacular.doodle.application.Application
import io.nacular.doodle.application.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.internal.runtime.ComposeWebInternalApi
import org.jetbrains.compose.web.internal.runtime.GlobalSnapshotManager
import org.jetbrains.compose.web.internal.runtime.JsMicrotasksDispatcher
import org.kodein.di.DI
import org.kodein.di.bindings.NoArgBindingDI

@OptIn(ComposeWebInternalApi::class)
actual fun initComposeContent(
    rootNode: RootNode,
    themesAndControl: List<DI.Module>,
    content: @Composable () -> Unit
): Composition {
    GlobalSnapshotManager.ensureStarted()
    val monotonicFrameClock: MonotonicFrameClock = DefaultMonotonicFrameClock

    val context = monotonicFrameClock + JsMicrotasksDispatcher()
    val recomposer = Recomposer(context)

    val composition = Composition(
        applier = DoodleNodeApplier(rootNode),
        parent = recomposer
    )

    CoroutineScope(context).launch(start = CoroutineStart.UNDISPATCHED) {
        recomposer.runRecomposeAndApplyChanges()
    }

    return composition
}

actual fun app(themesAndControl: List<DI.Module>, block: (NoArgBindingDI<*>) -> Application){
    application(modules = themesAndControl) {
        block(this)
    }
}
