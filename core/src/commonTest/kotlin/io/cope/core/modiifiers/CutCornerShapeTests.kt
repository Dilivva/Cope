package io.cope.core.modiifiers

import io.cope.core.fakes.getFakeCanvasDrawPathCallback
import io.cope.core.modifier.CutCornerShape
import io.cope.core.node.DoodleNode
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
import kotlin.test.assertEquals
import kotlin.test.expect


class CutCornerShapeTests {

    @Test
    fun testRenderWithStroke() {
        val expectedStroke = Stroke(Color.Pink.paint, 2.0)
        val expectedColor = Color.Black
        val shape = CutCornerShape(10)
        val canvasMock = getFakeCanvasDrawPathCallback { path, stroke, fill ->
            expect("M21.0,1.0L88.0,1.0L109.0,11.0L109.0,89.0L99.0,99.0L21.0,99.0L11.0,89.0L11.0,11.0L21.0,1.0Z"){ path.data }
            expect(expectedStroke){ stroke }
            expect(expectedColor.paint){ fill }
        }
        val doodleNodeMock = object: DoodleNode(){
            override val view: View
                get() = view {
                    bounds = Rectangle(x = 10, y = 0, width = 100, height = 100)
                }
            override val container: Container
                get() = container {  }


        }
        doodleNodeMock.backgroundColor = expectedColor
        doodleNodeMock.size = Size(100, 100)

        shape.render(canvasMock, doodleNodeMock, expectedStroke)
    }

    @Test
    fun testRenderWithoutStroke() {
        val expectedColor = Color.Black
        val shape = CutCornerShape(10)
        val canvasMock = getFakeCanvasDrawPathCallback { path, stroke, fill ->
            expect("M20.0,0.0L90.0,0.0L110.0,10.0L110.0,90.0L100.0,100.0L20.0,100.0L10.0,90.0L10.0,10.0L20.0,0.0Z"){ path.data }
            expect(null){ stroke }
            expect(expectedColor.paint){ fill }
        }
        val doodleNodeMock = object: DoodleNode(){
            override val view: View
                get() = view {
                    bounds = Rectangle(x = 10, y = 0, width = 100, height = 100)
                }
            override val container: Container
                get() = container {  }


        }
        doodleNodeMock.backgroundColor = expectedColor
        doodleNodeMock.size = Size(100, 100)

        shape.render(canvasMock, doodleNodeMock, null)
    }

    @Test
    fun testGetPath() {
        val expectedColor = Color.Black
        val shape = CutCornerShape(10)
        var oldPath: io.nacular.doodle.geometry.Path? = null
        val canvasMock = getFakeCanvasDrawPathCallback { path, stroke, fill ->
            if (oldPath == null) oldPath = path
            else assertEquals(oldPath, path)
        }
        val doodleNodeMock = object: DoodleNode(){
            override val view: View
                get() = view {
                    bounds = Rectangle(x = 10, y = 0, width = 100, height = 100)
                }
            override val container: Container
                get() = container {  }


        }
        doodleNodeMock.backgroundColor = expectedColor
        doodleNodeMock.size = Size(100, 100)

        shape.render(canvasMock, doodleNodeMock, null)
        shape.render(canvasMock, doodleNodeMock, null)
    }

}
