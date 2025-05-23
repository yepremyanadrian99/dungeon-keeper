package am.adrian.dungeonkeeper.behavior.node.condition

import am.adrian.dungeonkeeper.model.gameobject.trait.CanEat

class IsHungerFullCondition(
    private val creature: CanEat,
    override val name: String = "Is hunger level max",
    override val conditionSupplier: () -> Boolean = { creature.hungerLevel() == CanEat.MAX_HUNGER_LEVEL }
) : ConditionNode
