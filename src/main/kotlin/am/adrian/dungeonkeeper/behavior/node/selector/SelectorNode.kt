package am.adrian.dungeonkeeper.behavior.node.selector

import am.adrian.dungeonkeeper.behavior.node.ExpandableNode
import am.adrian.dungeonkeeper.behavior.node.Node

class SelectorNode(
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
