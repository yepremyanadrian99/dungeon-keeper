package am.adrian.dungeonkeeper.behavior.node.or

import am.adrian.dungeonkeeper.behavior.node.ExpandableNode
import am.adrian.dungeonkeeper.behavior.node.Node

class OrNode(
    override val name: String,
    override val childNodes: MutableList<Node> = ArrayList()
) : ExpandableNode {

    override fun execute(): Boolean {
        for (node in childNodes) {
            if (node.execute()) {
                return true
            }
        }
        return false
    }
}
