package io.cope.core.modiifiers

import io.cope.core.modifier.*
import io.cope.core.node.DoodleNode
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class SizeModifierTest {

    private lateinit var doodleNode: DoodleNode

    @BeforeTest
    fun setUp(){
        doodleNode = object: DoodleNode(){
            init {
                positionable = object: PositionableContainer{
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
    fun child_shouldFill_parent_MaxSize(){
        val modifier = Modifier.fillMaxSize()
        modifier.fold(Unit) { _, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(500,500)
        assertEquals(expected, doodleNode.size)
        assertEquals(expected, doodleNode.view.size)
        assertEquals(Size(500, 500), doodleNode.container.size)
    }

    @Test
    fun child_ShouldFillParent_With_padding(){
        val modifier = Modifier.padding(15).fillMaxSize()
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val childSize = Size(470, 470)
        val parentSize = Size(500, 500)
        assertEquals(childSize, doodleNode.view.size)
        assertEquals(parentSize, doodleNode.container.size)
    }

    @Test
    fun child_ShouldFillParentWidth(){
        val modifier = Modifier.fillMaxWidth()
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val childSize = Size(500, 0)
        val parentSize = Size(500, 0)
        assertEquals(childSize, doodleNode.view.size)
        assertEquals(parentSize, doodleNode.container.size)
    }
    @Test
    fun child_ShouldFillParentWidth_with_padding(){
        val modifier = Modifier.padding(left = 10, right = 10).fillMaxWidth()
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val childSize = Size(480, 0)
        val parentSize = Size(500, 0)
        assertEquals(childSize.width, doodleNode.view.width)
        assertEquals(parentSize.width, doodleNode.container.width)
    }

    @Test
    fun child_shouldFillParentHeight(){
        val modifier = Modifier.fillMaxHeight()
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val childSize = Size(500, 500)
        val parentSize = Size(500, 500)
        assertEquals(childSize.height, doodleNode.view.height)
        assertEquals(parentSize.height, doodleNode.container.height)
    }
    @Test
    fun child_shouldFillParentHeight_padding(){
        val modifier = Modifier.padding(top = 10, bottom = 10).fillMaxHeight()
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val childSize = Size(500, 480)
        val parentSize = Size(500, 500)
        assertEquals(childSize.height, doodleNode.view.height)
        assertEquals(parentSize.height, doodleNode.container.height)
    }

    @Test
    fun child_setSize(){
        val modifier = Modifier.size(100, 100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 100)
        assertEquals(expected, doodleNode.view.size)
        assertEquals(expected, doodleNode.container.size)
    }

    @Test
    fun child_setSize_padding(){
        val modifier = Modifier.padding(10).size(100, 100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(80, 80)
        val parent = Size(100, 100)
        assertEquals(expected, doodleNode.view.size)
        assertEquals(parent, doodleNode.size)
    }

    @Test
    fun child_setHeight(){
        val modifier = Modifier.height(100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(0, 100)
        assertEquals(expected.height, doodleNode.view.height)
        assertEquals(expected.height, doodleNode.height)
    }
    @Test
    fun child_setHeight_with_padding(){
        val modifier = Modifier.padding(10).height(100).width(100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 80)
        assertEquals(expected.height, doodleNode.view.height)
        assertEquals(100.0, doodleNode.height)
    }

    @Test
    fun child_setWidth(){
        val modifier = Modifier.width(100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 100)
        assertEquals(expected.width, doodleNode.view.width)
        assertEquals(expected.width, doodleNode.width)
    }

    @Test
    fun child_setWidth_padding(){
        val modifier = Modifier.padding(10).width(100).height(100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(80, 100)
        assertEquals(expected.width, doodleNode.view.width)
        assertEquals(100.0, doodleNode.width)
    }

    @Test
    fun child_setMinHeight_return_minHeight_ifSize_empty(){
        val modifier = Modifier.minHeight(100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(80, 100)
        assertEquals(expected.height, doodleNode.height)
    }
    @Test
    fun child_setMinWidth_return_minWidth_ifSize_empty(){
        val modifier = Modifier.minWidth(100)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 100)
        assertEquals(expected.width, doodleNode.width)
    }
    @Test
    fun child_setMinSize_return_minSize_ifSize_isLess(){
        val modifier = Modifier.minSize(100, 100).size(50, 50)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(100, 100)
        assertEquals(expected, doodleNode.size)
    }
    @Test
    fun child_setMinSize_return_Size_ifminSize_isLess(){
        val modifier = Modifier.minSize(100, 100).size(150, 150)
        modifier.fold(Unit){_, mod ->
            mod.apply(doodleNode)
        }
        val expected = Size(150, 150)
        assertEquals(expected, doodleNode.size)
    }


}