package io.cope.core.fakes

import io.nacular.doodle.core.Camera
import io.nacular.doodle.drawing.*
import io.nacular.doodle.geometry.*
import io.nacular.doodle.image.Image
import io.nacular.doodle.text.StyledText
import io.nacular.doodle.text.TextSpacing
import io.nacular.doodle.utils.TextAlignment
import io.nacular.measured.units.Angle
import io.nacular.measured.units.Measure

val fakeCanvas = object: Canvas {
    override var size: Size
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun arc(
        center: Point,
        radius: Double,
        sweep: Measure<Angle>,
        rotation: Measure<Angle>,
        fill: Paint
    ) {
        TODO("Not yet implemented")
    }

    override fun arc(
        center: Point,
        radius: Double,
        sweep: Measure<Angle>,
        rotation: Measure<Angle>,
        stroke: Stroke,
        fill: Paint?
    ) {
        TODO("Not yet implemented")
    }

    override fun circle(circle: Circle, fill: Paint) {
        TODO("Not yet implemented")
    }

    override fun circle(circle: Circle, stroke: Stroke, fill: Paint?) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun clip(ellipse: Ellipse, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun clip(path: Path, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun clip(polygon: Polygon, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun clip(rectangle: Rectangle, radius: Double, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun ellipse(ellipse: Ellipse, fill: Paint) {
        TODO("Not yet implemented")
    }

    override fun ellipse(ellipse: Ellipse, stroke: Stroke, fill: Paint?) {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun image(
        image: Image,
        destination: Rectangle,
        opacity: Float,
        radius: Double,
        source: Rectangle
    ) {
        TODO("Not yet implemented")
    }

    override fun line(start: Point, end: Point, stroke: Stroke) {
        TODO("Not yet implemented")
    }

    override fun path(path: Path, fill: Paint, fillRule: Renderer.FillRule?) {
        println("Patthy")
    }

    override fun path(path: Path, stroke: Stroke) {
        TODO("Not yet implemented")
    }

    override fun path(path: Path, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {
        TODO("Not yet implemented")
    }

    override fun path(points: List<Point>, fill: Paint, fillRule: Renderer.FillRule?) {
        TODO("Not yet implemented")
    }

    override fun path(points: List<Point>, stroke: Stroke) {
        TODO("Not yet implemented")
    }

    override fun path(points: List<Point>, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {
        TODO("Not yet implemented")
    }

    override fun poly(polygon: Polygon, fill: Paint) {
        TODO("Not yet implemented")
    }

    override fun poly(polygon: Polygon, stroke: Stroke, fill: Paint?) {
        TODO("Not yet implemented")
    }

    override fun rect(rectangle: Rectangle, fill: Paint) {
        TODO("Not yet implemented")
    }

    override fun rect(rectangle: Rectangle, stroke: Stroke, fill: Paint?) {
        TODO("Not yet implemented")
    }

    override fun rect(rectangle: Rectangle, radius: Double, fill: Paint) {
        TODO("Not yet implemented")
    }

    override fun rect(rectangle: Rectangle, radius: Double, stroke: Stroke, fill: Paint?) {
        TODO("Not yet implemented")
    }

    override fun shadow(shadow: Shadow, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun text(text: StyledText, at: Point, textSpacing: TextSpacing) {
        TODO("Not yet implemented")
    }

    override fun text(text: String, font: Font?, at: Point, fill: Paint, textSpacing: TextSpacing) {
        TODO("Not yet implemented")
    }
    

    override fun transform(transform: AffineTransform, camera: Camera, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun transform(transform: AffineTransform, block: Canvas.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun wedge(
        center: Point,
        radius: Double,
        sweep: Measure<Angle>,
        rotation: Measure<Angle>,
        fill: Paint
    ) {
        TODO("Not yet implemented")
    }

    override fun wedge(
        center: Point,
        radius: Double,
        sweep: Measure<Angle>,
        rotation: Measure<Angle>,
        stroke: Stroke,
        fill: Paint?
    ) {
        TODO("Not yet implemented")
    }

    override fun wrapped(
        text: StyledText,
        at: Point,
        width: Double,
        indent: Double,
        alignment: TextAlignment,
        lineSpacing: Float,
        textSpacing: TextSpacing
    ) {
        TODO("Not yet implemented")
    }

    override fun wrapped(
        text: String,
        at: Point,
        width: Double,
        fill: Paint,
        font: Font?,
        indent: Double,
        alignment: TextAlignment,
        lineSpacing: Float,
        textSpacing: TextSpacing
    ) {
        TODO("Not yet implemented")
    }


}

fun getFakeCanvasDrawPathCallback(block: (Path, Stroke?, Paint?) -> Unit): Canvas{
    return object: Canvas{
        override var size: Size
            get() = TODO("Not yet implemented")
            set(value) {}

        override fun arc(center: Point, radius: Double, sweep: Measure<Angle>, rotation: Measure<Angle>, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun arc(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            stroke: Stroke,
            fill: Paint?
        ) {
            TODO("Not yet implemented")
        }

        override fun circle(circle: Circle, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun circle(circle: Circle, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun clear() {
            TODO("Not yet implemented")
        }

        override fun clip(ellipse: Ellipse, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(path: Path, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(polygon: Polygon, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(rectangle: Rectangle, radius: Double, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun ellipse(ellipse: Ellipse, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun ellipse(ellipse: Ellipse, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun flush() {
            TODO("Not yet implemented")
        }

        override fun image(image: Image, destination: Rectangle, opacity: Float, radius: Double, source: Rectangle) {
            TODO("Not yet implemented")
        }

        override fun line(start: Point, end: Point, stroke: Stroke) {
            TODO("Not yet implemented")
        }

        override fun path(path: Path, fill: Paint, fillRule: Renderer.FillRule?) {
            block(path,null,fill)
        }

        override fun path(path: Path, stroke: Stroke) {
            block(path, stroke, null)
        }

        override fun path(path: Path, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {
            block(path, stroke, fill)
        }

        override fun path(points: List<Point>, fill: Paint, fillRule: Renderer.FillRule?) {
            TODO("Not yet implemented")
        }

        override fun path(points: List<Point>, stroke: Stroke) {
            TODO("Not yet implemented")
        }

        override fun path(points: List<Point>, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {
            TODO("Not yet implemented")
        }

        override fun poly(polygon: Polygon, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun poly(polygon: Polygon, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, radius: Double, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, radius: Double, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun shadow(shadow: Shadow, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun text(text: StyledText, at: Point, textSpacing: TextSpacing) {
            TODO("Not yet implemented")
        }

        override fun text(text: String, font: Font?, at: Point, fill: Paint, textSpacing: TextSpacing) {
            TODO("Not yet implemented")
        }


        override fun transform(transform: AffineTransform, camera: Camera, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun transform(transform: AffineTransform, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun wedge(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            fill: Paint
        ) {
            TODO("Not yet implemented")
        }

        override fun wedge(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            stroke: Stroke,
            fill: Paint?
        ) {
            TODO("Not yet implemented")
        }

        override fun wrapped(
            text: StyledText,
            at: Point,
            width: Double,
            indent: Double,
            alignment: TextAlignment,
            lineSpacing: Float,
            textSpacing: TextSpacing
        ) {
            TODO("Not yet implemented")
        }

        override fun wrapped(
            text: String,
            at: Point,
            width: Double,
            fill: Paint,
            font: Font?,
            indent: Double,
            alignment: TextAlignment,
            lineSpacing: Float,
            textSpacing: TextSpacing
        ) {
            TODO("Not yet implemented")
        }


    }
}

fun getFakeCanvasDrawCircleCallback(block: (Circle, Stroke?, Paint?) -> Unit): Canvas{
    return object: Canvas{
        override var size: Size
            get() = TODO("Not yet implemented")
            set(value) {}

        override fun arc(center: Point, radius: Double, sweep: Measure<Angle>, rotation: Measure<Angle>, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun arc(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            stroke: Stroke,
            fill: Paint?
        ) {
            TODO("Not yet implemented")
        }

        override fun circle(circle: Circle, fill: Paint) {
            block(circle, null, fill)
        }

        override fun circle(circle: Circle, stroke: Stroke, fill: Paint?) {
            block(circle, stroke, fill)
        }

        override fun clear() {
            TODO("Not yet implemented")
        }

        override fun clip(ellipse: Ellipse, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(path: Path, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(polygon: Polygon, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(rectangle: Rectangle, radius: Double, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun ellipse(ellipse: Ellipse, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun ellipse(ellipse: Ellipse, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun flush() {
            TODO("Not yet implemented")
        }

        override fun image(image: Image, destination: Rectangle, opacity: Float, radius: Double, source: Rectangle) {
            TODO("Not yet implemented")
        }

        override fun line(start: Point, end: Point, stroke: Stroke) {
            TODO("Not yet implemented")
        }

        override fun path(path: Path, fill: Paint, fillRule: Renderer.FillRule?) {
        }

        override fun path(path: Path, stroke: Stroke) {

        }

        override fun path(path: Path, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {

        }

        override fun path(points: List<Point>, fill: Paint, fillRule: Renderer.FillRule?) {
            TODO("Not yet implemented")
        }

        override fun path(points: List<Point>, stroke: Stroke) {
            TODO("Not yet implemented")
        }

        override fun path(points: List<Point>, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {
            TODO("Not yet implemented")
        }

        override fun poly(polygon: Polygon, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun poly(polygon: Polygon, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, radius: Double, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, radius: Double, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun shadow(shadow: Shadow, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun text(text: StyledText, at: Point, textSpacing: TextSpacing) {
            TODO("Not yet implemented")
        }

        override fun text(text: String, font: Font?, at: Point, fill: Paint, textSpacing: TextSpacing) {
            TODO("Not yet implemented")
        }


        override fun transform(transform: AffineTransform, camera: Camera, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun transform(transform: AffineTransform, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun wedge(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            fill: Paint
        ) {
            TODO("Not yet implemented")
        }

        override fun wedge(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            stroke: Stroke,
            fill: Paint?
        ) {
            TODO("Not yet implemented")
        }

        override fun wrapped(
            text: StyledText,
            at: Point,
            width: Double,
            indent: Double,
            alignment: TextAlignment,
            lineSpacing: Float,
            textSpacing: TextSpacing
        ) {
            TODO("Not yet implemented")
        }

        override fun wrapped(
            text: String,
            at: Point,
            width: Double,
            fill: Paint,
            font: Font?,
            indent: Double,
            alignment: TextAlignment,
            lineSpacing: Float,
            textSpacing: TextSpacing
        ) {
            TODO("Not yet implemented")
        }


    }
}
fun getFakeCanvasDrawRectCallback(block: (Rectangle, Stroke?, Paint?) -> Unit): Canvas{
    return object: Canvas{
        override var size: Size
            get() = TODO("Not yet implemented")
            set(value) {}

        override fun arc(center: Point, radius: Double, sweep: Measure<Angle>, rotation: Measure<Angle>, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun arc(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            stroke: Stroke,
            fill: Paint?
        ) {
            TODO("Not yet implemented")
        }

        override fun circle(circle: Circle, fill: Paint) {

        }

        override fun circle(circle: Circle, stroke: Stroke, fill: Paint?) {

        }

        override fun clear() {
            TODO("Not yet implemented")
        }

        override fun clip(ellipse: Ellipse, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(path: Path, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(polygon: Polygon, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun clip(rectangle: Rectangle, radius: Double, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun ellipse(ellipse: Ellipse, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun ellipse(ellipse: Ellipse, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun flush() {
            TODO("Not yet implemented")
        }

        override fun image(image: Image, destination: Rectangle, opacity: Float, radius: Double, source: Rectangle) {
            TODO("Not yet implemented")
        }

        override fun line(start: Point, end: Point, stroke: Stroke) {
            TODO("Not yet implemented")
        }

        override fun path(path: Path, fill: Paint, fillRule: Renderer.FillRule?) {
        }

        override fun path(path: Path, stroke: Stroke) {

        }

        override fun path(path: Path, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {

        }

        override fun path(points: List<Point>, fill: Paint, fillRule: Renderer.FillRule?) {
            TODO("Not yet implemented")
        }

        override fun path(points: List<Point>, stroke: Stroke) {
            TODO("Not yet implemented")
        }

        override fun path(points: List<Point>, stroke: Stroke, fill: Paint, fillRule: Renderer.FillRule?) {
            TODO("Not yet implemented")
        }

        override fun poly(polygon: Polygon, fill: Paint) {
            TODO("Not yet implemented")
        }

        override fun poly(polygon: Polygon, stroke: Stroke, fill: Paint?) {
            TODO("Not yet implemented")
        }

        override fun rect(rectangle: Rectangle, fill: Paint) {
            block(rectangle, null, fill)
        }

        override fun rect(rectangle: Rectangle, stroke: Stroke, fill: Paint?) {
            block(rectangle, stroke, fill)
        }

        override fun rect(rectangle: Rectangle, radius: Double, fill: Paint) {
            block(rectangle, null, fill)
        }

        override fun rect(rectangle: Rectangle, radius: Double, stroke: Stroke, fill: Paint?) {
            block(rectangle, stroke, fill)
        }

        override fun shadow(shadow: Shadow, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun text(text: StyledText, at: Point, textSpacing: TextSpacing) {
            TODO("Not yet implemented")
        }

        override fun text(text: String, font: Font?, at: Point, fill: Paint, textSpacing: TextSpacing) {
            TODO("Not yet implemented")
        }


        override fun transform(transform: AffineTransform, camera: Camera, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun transform(transform: AffineTransform, block: Canvas.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun wedge(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            fill: Paint
        ) {
            TODO("Not yet implemented")
        }

        override fun wedge(
            center: Point,
            radius: Double,
            sweep: Measure<Angle>,
            rotation: Measure<Angle>,
            stroke: Stroke,
            fill: Paint?
        ) {
            TODO("Not yet implemented")
        }

        override fun wrapped(
            text: StyledText,
            at: Point,
            width: Double,
            indent: Double,
            alignment: TextAlignment,
            lineSpacing: Float,
            textSpacing: TextSpacing
        ) {
            TODO("Not yet implemented")
        }

        override fun wrapped(
            text: String,
            at: Point,
            width: Double,
            fill: Paint,
            font: Font?,
            indent: Double,
            alignment: TextAlignment,
            lineSpacing: Float,
            textSpacing: TextSpacing
        ) {
            TODO("Not yet implemented")
        }



    }
}