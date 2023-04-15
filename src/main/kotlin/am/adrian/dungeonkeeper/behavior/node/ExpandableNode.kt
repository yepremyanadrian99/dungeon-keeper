package am.adrian.dungeonkeeper.behavior.node

interface ExpandableNode : Node {

    val childNodes: MutableList<Node>

    fun addChild(node: Node) {
        this.childNodes += node
    }
}
