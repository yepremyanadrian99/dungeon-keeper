package am.adrian.dungeonkeeper.behavior.node.condition

import am.adrian.dungeonkeeper.model.gameobject.trait.CanEat

class IsHungryCondition(
    private val creature: CanEat,
    override val name: String = "Is creature hungry",
    override val conditionSupplier: () -> Boolean = { creature.isHungry() }
) : ConditionNode
