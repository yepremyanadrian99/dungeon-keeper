package am.adrian.dungeonkeeper.behavior.node.action

import am.adrian.dungeonkeeper.model.gameobject.trait.CanEat

class EatAction(
    private val creature: CanEat,
    override val name: String = "Is creature hungry",
    override val action: Runnable = Runnable { creature.eat() }
) : ActionNode
