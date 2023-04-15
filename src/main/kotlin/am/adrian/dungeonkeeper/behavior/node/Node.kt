package am.adrian.dungeonkeeper.behavior.node

interface Node {

    val name: String

    fun execute(): Boolean
}
