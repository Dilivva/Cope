package io.coodle.core.node

import io.nacular.doodle.core.view
import kotlin.test.Test
import kotlin.test.expect

class DoodleNodeApplierTest {

    private val rootNode = RootNode()
    private val nodeApplier = DoodleNodeApplier(rootNode)
    @Test
    fun insertChildren(){
        val viewNode1 = ViewNode(view {  })
        val viewNode2 = ViewNode(view {  })
        val viewNode3 = ViewNode(view {  })
        nodeApplier.insertBottomUp(0, viewNode1)
        nodeApplier.insertBottomUp(1, viewNode2)
        nodeApplier.insertBottomUp(2, viewNode3)
        expect(listOf(viewNode1,viewNode2,viewNode3)){
            rootNode.doodleChildren.toList()
        }
    }

    @Test
    fun insert_in_same_postion_move_Children(){
        val viewNode1 = ViewNode(view {  })
        val viewNode2 = ViewNode(view {  })
        val viewNode3 = ViewNode(view {  })
        nodeApplier.insertBottomUp(0, viewNode1)
        nodeApplier.insertBottomUp(0, viewNode2)
        nodeApplier.insertBottomUp(0, viewNode3)
        expect(listOf(viewNode3,viewNode2,viewNode1)){
            rootNode.doodleChildren.toList()
        }
    }

    @Test
    fun moveChildren(){
        val viewNode1 = ViewNode(view {  })
        val viewNode2 = ViewNode(view {  })
        val viewNode3 = ViewNode(view {  })

        nodeApplier.insertBottomUp(0, viewNode1)
        nodeApplier.insertBottomUp(1, viewNode2)
        nodeApplier.insertBottomUp(2, viewNode3)
        nodeApplier.move(0,3,1)

        expect(listOf(viewNode2,viewNode3,viewNode1)){
            rootNode.doodleChildren.toList()
        }
    }

    @Test
    fun removeChildren(){
        val viewNode1 = ViewNode(view {  })
        val viewNode2 = ViewNode(view {  })
        val viewNode3 = ViewNode(view {  })

        nodeApplier.insertBottomUp(0, viewNode1)
        nodeApplier.insertBottomUp(1, viewNode2)
        nodeApplier.insertBottomUp(2, viewNode3)
        nodeApplier.remove(0,2)

        expect(listOf(viewNode3)){
            rootNode.doodleChildren.toList()
        }
    }

    @Test
    fun removeChildrenInDeep(){
        val viewNode1 = ViewNode(view {  })
        val viewNode2 = ViewNode(view {  })
        val viewNode3 = ViewNode(view {  })

        nodeApplier.insertBottomUp(0, viewNode1)
        nodeApplier.insertBottomUp(1, viewNode2)
        nodeApplier.insertBottomUp(2, viewNode3)
        nodeApplier.insertBottomUp(2, viewNode3)
        nodeApplier.remove(2,2)

        expect(listOf(viewNode1, viewNode2)){
            rootNode.doodleChildren.toList()
        }
    }

    @Test
    fun clearChildren(){
        val viewNode1 = ViewNode(view {  })
        val viewNode2 = ViewNode(view {  })
        val viewNode3 = ViewNode(view {  })

        nodeApplier.insertBottomUp(0, viewNode1)
        nodeApplier.insertBottomUp(1, viewNode2)
        nodeApplier.insertBottomUp(2, viewNode3)
        nodeApplier.clear()

        expect(listOf()){
            rootNode.doodleChildren.toList()
        }
    }
}