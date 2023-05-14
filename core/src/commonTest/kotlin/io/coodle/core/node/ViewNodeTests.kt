package io.coodle.core.node

import io.coodle.core.drawing.Drawing
import io.coodle.core.fakes.fakeCanvas
import io.coodle.core.layout.Alignment
import io.coodle.core.layout.ColumnScope
import io.coodle.core.modifier.Modifier
import io.coodle.core.modifier.Padding
import io.nacular.doodle.core.Positionable
import io.nacular.doodle.core.PositionableContainer
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.*
import io.nacular.doodle.geometry.*
import io.nacular.doodle.layout.Insets
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.expect

class ViewNodeTests {

    private lateinit var viewNode: ViewNode
    private val positionable = object: PositionableContainer{
        override val children: List<Positionable>
            get() = TODO("Not yet implemented")
        override val height: Double
            get() = 100.0
        override var idealSize: Size?
            get() = TODO("Not yet implemented")
            set(value) {}
        override val insets: Insets
            get() = TODO("Not yet implemented")
        override var minimumSize: Size
            get() = TODO("Not yet implemented")
            set(value) {}
        override val size: Size
            get() = TODO("Not yet implemented")
        override val width: Double
            get() = 100.0

    }

    @BeforeTest
    fun setUp(){
        viewNode = ViewNode(view {  })
    }

    @Test
    fun given_set_padding(){
        val padding = Padding(10,10,10,10)
        viewNode.padding = padding
        expect(padding){viewNode.padding}
    }
    @Test
    fun given_height(){
        val height = 40.0
        viewNode.height = height
        expect(height){viewNode.height}
    }
    @Test
    fun given_width(){
        val height = 40.0
        viewNode.width = height
        expect(height){viewNode.width}
    }
    @Test
    fun given_container_contains_a_child(){
        expect(1){ viewNode.container.children.size }
    }
    @Test
    fun given_view_is_added(){
        val view = view {  }
        viewNode = ViewNode(view)
        expect(view){ viewNode.view }
    }
    @Test
    fun given_minWidth_return_same_for_width(){
        val minWidth = 50.0
        viewNode.minWidth = minWidth
        expect(minWidth){ viewNode.width }
    }
    @Test
    fun given_minHeight_return_same_for_height(){
        val minHeight = 50.0
        viewNode.minHeight = minHeight
        expect(minHeight){ viewNode.height }
    }

    @Test
    fun given_height_plus_vertical_padding_return_padded_view(){
        val height = 50.0
        val padding = Padding(10,10,10,10)
        viewNode.padding = padding
        viewNode.height = height

        expect(30.0){ viewNode.view.height }
    }
    @Test
    fun given_height_and_higher_minHeight_plus_vertical_padding_return_padded_view(){
        val height = 50.0
        val padding = Padding(10,10,10,10)
        val minHeight = 70.0
        viewNode.padding = padding
        viewNode.height = height
        viewNode.minHeight = minHeight

        expect(70.0){ viewNode.view.height }
    }
    @Test
    fun given_height_and_lower_minHeight_plus_vertical_padding_return_padded_view(){
        val height = 50.0
        val padding = Padding(10,10,10,10)
        val minHeight = 30.0
        viewNode.padding = padding
        viewNode.height = height
        viewNode.minHeight = minHeight

        expect(30.0){ viewNode.view.height }
    }

    @Test
    fun given_width_plus_horizontal_padding_return_padded_view(){
        val width = 50.0
        val padding = Padding(10,10,10,10)
        viewNode.padding = padding
        viewNode.width = width

        expect(30.0){ viewNode.view.width }
    }
    @Test
    fun given_width_and_higher_minWidth_plus_horizontal_padding_return_padded_view(){
        val height = 50.0
        val padding = Padding(10,10,10,10)
        val minHeight = 70.0
        viewNode.padding = padding
        viewNode.height = height
        viewNode.minHeight = minHeight

        expect(70.0){ viewNode.view.height }
    }
    @Test
    fun given_width_and_lower_minWidth_plus_horizontal_padding_return_padded_view(){
        val height = 50.0
        val padding = Padding(10,10,10,10)
        val minHeight = 30.0
        viewNode.padding = padding
        viewNode.height = height
        viewNode.minHeight = minHeight

        expect(30.0){ viewNode.view.height }
    }

    @Test
    fun given_maxSize_return_true(){
        viewNode.positionable = positionable
        expect(Size(100.0, 100.0)){ viewNode.maxSize }
    }
    @Test
    fun given_x_and_y_measure_return_correct_bounds(){

        viewNode.size = Size(100.0, 100.0)
        val bounds = viewNode.measure(10.0, 0.0, positionable)
        expect(Rectangle(x = 10.0,y = 0.0, height = 100.0, width = 100.0)){ bounds }
    }
    @Test
    fun given_alignment_measure_return_correct_bounds(){

        val columnScope = object: ColumnScope{
            override fun Modifier.weight(weight: Float): Modifier {
                TODO("Not yet implemented")
            }

            override fun Modifier.align(alignment: Alignment.Horizontal): Modifier {
                return then(alignment)
            }

        }
        viewNode.size = Size(50.0, 50.0)
        viewNode.modifier = with(columnScope){
            Modifier.align(Alignment.Horizontal.CenterHorizontally)
        }
        val bounds = viewNode.measure(10.0, 0.0, positionable)
        expect(Rectangle(x = 25.0,y = 0.0, height = 50.0, width = 50.0)){ bounds }
    }

    @Test
    fun given_ConstraintLayout_return_view_with_padding_applied(){
        viewNode.size = Size(50.0, 50.0)
        viewNode.padding = Padding(10,10,10,10)
        viewNode.container.layout?.layout(positionable)
        expect(Size(30.0,30.0)){ viewNode.view.size }
        expect(Size(50.0, 50.0)){ viewNode.container.size }
    }

    @Test
    fun given_draw_return_draw_called(){
        viewNode.view.bounds = Rectangle(width = 50.0, height = 50.0)
        viewNode.drawing = object: Drawing{
            override fun draw(canvas: Canvas, doodleNode: DoodleNode) {
                expect(viewNode){ doodleNode}
                expect(fakeCanvas){ canvas }
            }

        }
        viewNode.container.render(fakeCanvas)
    }

}