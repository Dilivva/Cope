package io.coodle.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.coodle.core.layout.Box
import io.coodle.core.layout.Column
import io.coodle.core.layout.Row
import io.coodle.core.modifier.Modifier
import io.coodle.core.modifier.fillMaxSize
import io.coodle.core.modifier.fillMaxWidth
import io.coodle.core.modifier.size
import io.coodle.core.test_utils.runTest
import io.nacular.doodle.geometry.Size
import kotlin.test.Test

class TestExmp {


    @Test
    fun test_Example() = runTest {

        var st by mutableStateOf(0)
        composition {
            Row(modifier = Modifier.fillMaxSize()){

            }
            Column(modifier = Modifier.size(st)) {
                Box(modifier = Modifier.fillMaxWidth()){}
            }
        }

        root.view.size = Size(500, 500)

        st = 50

        waitForRecompositionComplete()
        val second = root.children[1].size
        println("Root: ${root.size}")
        //val child = nextChild<ContainerNodeImpl>()
        //println("Child: ${child.size}")
        //assertIs<ContainerNode>(root.doodleChildren.first())

    }
}