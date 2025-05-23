package am.adrian.dungeonkeeper.behavior.node.loop

import am.adrian.dungeonkeeper.behavior.node.ExpandableNode
import am.adrian.dungeonkeeper.behavior.node.Node

class WhileNode(
    override val name: String,
    override val childNodes: MutableList<Node> = ArrayList()
) : ExpandableNode {

    override fun execute(): Boolean {
        while (true) {
            var allSucceeded = true
            for (child in childNodes) {
                if (!child.execute()) {
                    allSucceeded = false
                    break
                }
            }
            if (allSucceeded) {
                return true
            }
        }
    }
}
