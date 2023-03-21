package io.coodle.core.node

import io.nacular.doodle.application.Application
import io.nacular.doodle.core.*
import io.nacular.doodle.core.Layout.Companion.simpleLayout
import io.nacular.doodle.theme.ThemeManager
import io.nacular.doodle.theme.adhoc.DynamicTheme
import io.nacular.doodle.utils.ObservableList
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.instance

class RootNode: ContainerNode() {

     override val view = container {
        layout = simpleLayout { container ->
            val x = 0.0
            val y = 0.0
            doodleChildren.forEachIndexed { index, doodleNode ->
                container.children[index].bounds = doodleNode.measure(x, y, container)
            }
        }

    }
    override val container: Container = view

    override val children: ObservableList<View> = view.children


    fun getApplication(app: NoArgBindingDI<*>): Application {
        //TODO: to make this customizable
        // Either remove ThemeManager and DynamicTheme for an extra design system on top of core
        val display: Display = app.instance()
        val manager: ThemeManager = app.instance()
        val theme: DynamicTheme = app.instance()
        return object : Application {
            init {
                manager.selected = theme
                view.size = display.size
                display += view
            }

            override fun shutdown() {}

        }
    }

    override fun recalculateSize() { /* No-op */ }

    override fun toString(): String {
        return "RootNode"
    }

}