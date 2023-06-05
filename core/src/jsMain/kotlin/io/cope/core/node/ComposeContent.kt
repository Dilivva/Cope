package io.cope.core.node

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.internal.runtime.ComposeWebInternalApi
import org.jetbrains.compose.web.internal.runtime.GlobalSnapshotManager
import org.jetbrains.compose.web.internal.runtime.JsMicrotasksDispatcher
import org.kodein.di.DI

@OptIn(ComposeWebInternalApi::class)
fun setContentOld(
    themesAndControl: List<DI.Module>,
    content: @Composable () -> Unit
): Composition {
    GlobalSnapshotManager.ensureStarted()
    val monotonicFrameClock: MonotonicFrameClock = DefaultMonotonicFrameClock

    val context = monotonicFrameClock + JsMicrotasksDispatcher()
    val recomposer = Recomposer(context)

    val columnNode = RootNode()
    val composition = Composition(
        applier = DoodleNodeApplier(columnNode),
        parent = recomposer
    )

    CoroutineScope(context).launch(start = CoroutineStart.UNDISPATCHED) {
        recomposer.runRecomposeAndApplyChanges()
    }
//    application(modules = themesAndControl) {
//        //columnNode.getApplication(this)
//    }

    composition.setContent @Composable {
        content()
    }

    return composition
}