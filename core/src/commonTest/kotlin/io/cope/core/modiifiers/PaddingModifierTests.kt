package io.cope.core.modiifiers

import io.cope.core.modifier.Modifier
import io.cope.core.modifier.padding
import io.cope.core.node.DoodleNode
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PaddingModifierTests {

    private lateinit var doodleNode: DoodleNode

    @BeforeTest
    fun setUp(){
        doodleNode = object: DoodleNode(){
            init {
                positionable = object: PositionableContainer {
                    override val children: List<Positionable>
                        get() = TODO("Not yet implemented")
                    override val height: Double
                        get() = 500.0
                    override var idealSize: Size?
                        get() = TODO("Not yet implemented")
                        set(value) {}
                    override val insets: Insets
                        get() = TODO("Not yet implemented")
                    override var minimumSize: Size
                        get() = Size(500, 500)
                        set(value) {}
                    override val size: Size
                        get() = Size(500, 500)
                    override val width: Double
                        get() = 500.0

                }
            }
            override val view: View = view {}
            override val container: Container = container {}

        }
    }

    @Test
    fun child_shouldPad_all(){
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(all = 10)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100,100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(80, 80), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }

    @Test
    fun child_shouldPad_top(){
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(top = 10)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100,100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(100, 90), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }

    @Test
    fun child_shouldPad_bottom(){
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(bottom = 10)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100,100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(100, 90), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }
    @Test
    fun child_shouldPad_left(){
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(left = 10)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100,100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(90, 100), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }
    @Test
    fun child_shouldPad_right(){
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(right = 10)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100,100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(90, 100), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }

    @Test
    fun child_shouldPad_vertical() {
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(vertical = 20)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(100, 60), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }

    @Test
    fun child_shouldPad_horizontal() {
        doodleNode.size = Size(100, 100)
        val modifier = Modifier.padding(horizontal = 10)
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 100)
        assertEquals(expected, doodleNode.size)
        assertEquals(Size(80, 100), doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }


}