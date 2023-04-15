package am.adrian.dungeonkeeper.behavior.node.sequence

import am.adrian.dungeonkeeper.behavior.node.ExpandableNode
import am.adrian.dungeonkeeper.behavior.node.Node

class SequenceNode(
    override val name: String,
    override val childNodes: MutableList<Node> = ArrayList()
) : ExpandableNode {

    override fun execute(): Boolean {
        for (node in childNodes) {
            if (!node.execute()) {
                return false
            }
        }
        return true
    }
}
