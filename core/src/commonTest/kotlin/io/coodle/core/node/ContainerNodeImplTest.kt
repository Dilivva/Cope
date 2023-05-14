package io.coodle.core.node


import io.coodle.core.layout.LayoutMeasurement
import io.nacular.doodle.core.*
import io.nacular.doodle.geometry.Size
import io.nacular.doodle.layout.Insets
import kotlin.test.*

class ContainerNodeImplTest {

    private lateinit var containerNodeImpl: ContainerNodeImpl
    private val positionableContainer = object: PositionableContainer{
        override val children: List<Positionable>
            get() = listOf()
        override val height: Double
            get() = 500.0
        override var idealSize: Size?
            get() = TODO("Not yet implemented")
            set(value) {}
        override val insets: Insets
            get() = TODO("Not yet implemented")
        override var minimumSize: Size
            get() = TODO("Not yet implemented")
            set(value) {}
        override val size: Size
            get() = Size(500, 500)
        override val width: Double
            get() = 500.0

    }
    @AfterTest
    fun tearDown() {

    }

    @BeforeTest
    fun setUp() {
        val layoutMeasurement = object: LayoutMeasurement{
            override fun layout(
                doodleViews: List<DoodleNode>,
                positionableContainer: PositionableContainer,
                parent: DoodleNode
            ) {}

            override fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size {
                return Size(children.sumOf { it.width }, children.sumOf { it.height })
            }
            override fun debugInfo(): String {
                TODO("Not yet implemented")
            }

        }
        containerNodeImpl = ContainerNodeImpl(layoutMeasurement)
    }

    @Test
    fun addChildren(){
        val view1 = view {  }
        val view2 = view {  }

        containerNodeImpl.children.add(view1)
        containerNodeImpl.children.add(view2)
        assertEquals(2, containerNodeImpl.children.size)
    }

    @Test
    fun parentSize_change(){
        val layoutMeasurement = object: LayoutMeasurement{
            override fun layout(
                doodleViews: List<DoodleNode>,
                positionableContainer: PositionableContainer,
                parent: DoodleNode
            ) {}

            override fun getSize(parent: DoodleNode, children: List<DoodleNode>): Size {
                return Size(children.maxOf { it.width }, children.maxOf { it.height })
            }

            override fun debugInfo(): String { return "" }

        }
        val containerNode = ContainerNodeImpl(layoutMeasurement)
        val viewNode = ViewNode(view {  })
        viewNode.size = Size(500, 500)
        containerNode.view.children += viewNode.container
        containerNode.doodleChildren += viewNode
        containerNode.view.layout?.layout(positionableContainer)

        val size = containerNode.size
        expect(Size(500, 500)){ size }
    }
}
