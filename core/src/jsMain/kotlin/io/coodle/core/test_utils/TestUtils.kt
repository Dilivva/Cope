package io.coodle.core.test_utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import io.coodle.core.content.initComposeContent
import io.coodle.core.node.RootNode
import io.nacular.doodle.HTMLElement
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private class TestMonotonicClockImpl(
    private val onRecomposeComplete: () -> Unit
) : MonotonicFrameClock {

    override suspend fun <R> withFrameNanos(
        onFrame: (Long) -> R
    ): R = suspendCoroutine { continuation ->
        window.requestAnimationFrame {
            val duration = it.toDuration(DurationUnit.MILLISECONDS)
            val result = onFrame(duration.inWholeNanoseconds)
            continuation.resume(result)
            onRecomposeComplete()
        }
    }
}

class TestScope : CoroutineScope by MainScope() {

    /**
     * It's used as a parent element for the composition.
     * It's added into the document's body automatically.
     */
    val root = RootNode()

    private var waitForRecompositionCompleteContinuation: Continuation<Unit>? = null
    private val childrenIterator = root.children.listIterator()

    init {

    }

    private fun onRecompositionComplete() {
        waitForRecompositionCompleteContinuation?.resume(Unit)
        waitForRecompositionCompleteContinuation = null
    }

    /**
     * Cleans up the [root] content.
     * Creates a new composition with a given Composable [content].
     */

    fun composition(content: @Composable () -> Unit) {
        root.clear()

       val composition = initComposeContent(root, listOf(), TestMonotonicClockImpl(
           onRecomposeComplete = ::onRecompositionComplete
       ), content)
       composition.setContent(content)
    }

    /**
     * @return a reference to the next child element of the root.
     * Subsequent calls will return next child reference every time.
     */
    fun nextChild() = nextChild<HTMLElement>()


    @Suppress("UNCHECKED_CAST")
    fun <T> nextChild() = childrenIterator.next() as T

    /**
     * Suspends until element with [elementId] observes any change to its html.
     */
//    suspend fun waitForChanges(elementId: String) {
//        waitForChanges(document.getElementById(elementId) as HTMLElement)
//    }

    /**
     * Suspends until [element] observes any change to its html.
     */
//    suspend fun waitForChanges(element: HTMLElement = root) {
//        suspendCoroutine<Unit> { continuation ->
//            val observer = MutationObserver { _, observer ->
//                continuation.resume(Unit)
//                observer.disconnect()
//            }
//            observer.observe(element, MutationObserverOptions)
//        }
//    }

    /**
     * Suspends until recomposition completes.
     */
    suspend fun waitForRecompositionComplete() {
        suspendCoroutine<Unit> { continuation ->
            waitForRecompositionCompleteContinuation = continuation
        }
    }
}

/**
 * Use this method to test compose-web components rendered using HTML.
 * Declare states and make assertions in [block].
 * Use [TestScope.composition] to define the code under test.
 *
 * For dynamic tests, use [TestScope.waitForRecompositionComplete]
 * after changing state's values and before making assertions.
 *
 * @see [TestScope.composition]
 * @see [TestScope.waitForRecompositionComplete]
 * @see [TestScope.waitForChanges].
 *
 * Test example:
 * ```
 * @Test
 * fun textChild() = runTest {
 *      var textState by mutableStateOf("inner text")
 *
 *      composition {
 *          Div {
 *              Text(textState)
 *          }
 *      }
 *      assertEquals("<div>inner text</div>", root.innerHTML)
 *
 *      textState = "new text"
 *      waitForRecompositionComplete()
 *
 *      assertEquals("<div>new text</div>", root.innerHTML)
 * }
 * ```
 */
fun runTest(block: suspend TestScope.() -> Unit): dynamic {
    val scope = TestScope()
    return scope.promise { block(scope) }
}
