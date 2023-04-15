package am.adrian.dungeonkeeper.behavior.node.action

import am.adrian.dungeonkeeper.behavior.node.Node

interface ActionNode : Node {

    val action: Runnable

    override fun execute(): Boolean {
        action.run()
        return true
    }
}
