package io.coodle.core.modiifiers

import io.coodle.core.fakes.getFakeCanvasDrawRectCallback
import io.coodle.core.modifier.RectangleShape
import io.coodle.core.node.DoodleNode
import io.nacular.doodle.core.Container
import io.nacular.doodle.core.View
import io.nacular.doodle.core.container
import io.nacular.doodle.core.view
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.Stroke
import io.nacular.doodle.drawing.paint
import io.nacular.doodle.geometry.Rectangle
import io.nacular.doodle.geometry.Size
import kotlin.test.Test
import kotlin.test.expect

class RectangleShapeTests {

    @Test
    fun testRenderWithStroke() {
        val expectedStroke = Stroke(Color.Pink.paint, 2.0)
        val expectedColor = Color.Black
        val rect = Rectangle(x = 10, y = 0, width = 100, height = 100)
        val shape = RectangleShape

        val canvasMock = getFakeCanvasDrawRectCallback { recti, stroke, paint ->
            expect(98.0){ recti.width }
            expect(98.0){ recti.height }
            expect(expectedStroke){ stroke }
            expect(expectedColor.paint){ paint }
        }
        val doodleNodeMock = object: DoodleNode(){
            override val view: View
                get() = view {
                    bounds = rect
                }
            override val container: Container
                get() = container {  }


        }
        doodleNodeMock.backgroundColor = expectedColor
        doodleNodeMock.size = Size(100, 100)

        shape.render(canvasMock, doodleNodeMock, expectedStroke)
    }
    @Test
    fun testRenderWithOutStroke() {
        val expectedColor = Color.Black
        val rect = Rectangle(x = 10, y = 0, width = 100, height = 100)
        val shape = RectangleShape

        val canvasMock = getFakeCanvasDrawRectCallback { recti, stroke, paint ->
            expect(100.0){ recti.width }
            expect(100.0){ recti.height }
            expect(null){ stroke }
            expect(expectedColor.paint){ paint }
        }
        val doodleNodeMock = object: DoodleNode(){
            override val view: View
                get() = view {
                    bounds = rect
                }
            override val container: Container
                get() = container {  }


        }
        doodleNodeMock.backgroundColor = expectedColor
        doodleNodeMock.size = Size(100, 100)

        shape.render(canvasMock, doodleNodeMock, null)
    }
}