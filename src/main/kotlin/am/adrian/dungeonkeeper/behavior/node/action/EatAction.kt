package am.adrian.dungeonkeeper.behavior.node.action

import am.adrian.dungeonkeeper.model.gameobject.trait.CanEat
import org.apache.logging.log4j.kotlin.Logging

class EatAction(
    private val creature: CanEat,
    override val name: String = "Creature eats",
    override val action: Runnable = Runnable {
        logger.info { "Creature is eating" }
        Thread.sleep(1000)
        creature.adjustHunger(33)
        logger.info { "Creature's new hunger level: ${creature.hungerLevel()}" }
    }
) : ActionNode {

    companion object : Logging
}
