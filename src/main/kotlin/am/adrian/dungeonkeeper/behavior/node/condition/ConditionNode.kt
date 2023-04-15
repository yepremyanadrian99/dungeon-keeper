package am.adrian.dungeonkeeper.behavior.node.condition

import am.adrian.dungeonkeeper.behavior.node.Node

interface ConditionNode : Node {

    val conditionSupplier: () -> Boolean

    override fun execute(): Boolean = conditionSupplier.invoke()
}
