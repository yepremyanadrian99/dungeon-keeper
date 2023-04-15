package am.adrian.dungeonkeeper.behavior

import am.adrian.dungeonkeeper.behavior.node.Node

interface BehaviorTree {

    val rootNode: Node

    fun execute() {
        rootNode.execute()
    }
}
