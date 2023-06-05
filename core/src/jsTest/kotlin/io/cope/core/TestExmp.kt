package io.cope.core

import androidx.compose.runtime.*
import io.cope.core.layout.Box
import io.cope.core.layout.Column
import io.cope.core.modifier.*
import io.cope.core.test_utils.runTest
import kotlinx.coroutines.delay
import kotlin.test.Test

class TestExmp {


    @Test
    fun test_Example() = runTest {


        composition {
            var st by  remember {  mutableStateOf(100) }
            Column(modifier = Modifier.height(st)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    LaunchedEffect(st){
                        println("LE: $st")
                        delay(1000)
                        st = 500
                        println("ST: $st")
                    }
                }
            }
        }


        waitForRecompositionComplete()
        //val second = root.children[1].size
        println("Root: ${root.children.size}")
        //println("Save: ${root.size}")
        //val child = nextChild<ContainerNodeImpl>()
        //println("Child: ${child.size}")
        //assertIs<ContainerNode>(root.doodleChildren.first())

    }
}