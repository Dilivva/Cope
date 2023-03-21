package io.coodle.core.node

import androidx.compose.runtime.*
import io.nacular.doodle.application.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.internal.runtime.ComposeWebInternalApi
import org.jetbrains.compose.web.internal.runtime.GlobalSnapshotManager
import org.jetbrains.compose.web.internal.runtime.JsMicrotasksDispatcher
import org.kodein.di.DI

@OptIn(ComposeWebInternalApi::class)
fun setContent(
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

    CoroutineScope(context).launch(start = CoroutineStart.UNDISPATCHED) {
//        val themes = listOf(
//            BasicTheme.basicButtonBehavior(),
//            BasicTheme.basicLabelBehavior(),
//            NativeTheme.nativeTextFieldBehavior(),
//            BasicTheme.basicSwitchBehavior(),
//            NativeTheme.nativeScrollPanelBehavior(),
//            BasicTheme.basicListBehavior()
//        )
//        val controls = listOf(Modules.PointerModule, Modules.ImageModule)
//        val allModules = themes + controls
        application(modules = themesAndControl) {
            columnNode.getApplication(this)
        }

    }

    composition.setContent @Composable {
        content()
    }

    return composition
}