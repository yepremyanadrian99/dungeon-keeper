package am.adrian.dungeonkeeper.game.events

import am.adrian.dungeonkeeper.game.Game
import am.adrian.dungeonkeeper.model.gameobject.trait.CanEat
import am.adrian.dungeonkeeper.observer.Observable
import am.adrian.dungeonkeeper.observer.Observer
import am.adrian.dungeonkeeper.timer.GameTickTriggerer
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Component

@Component
class CreatureHungerEvent(
    private val game: Game,
    gameTickTriggerer: GameTickTriggerer,
) : Observer {

    companion object : Logging

    init {
        gameTickTriggerer.addObserver(this)
    }

    override fun updated(o: Observable) {
        logger.info { "Updating creature hunger levels" }
        game.creatures
            .filterIsInstance<CanEat>()
            .forEach { eatingCreature -> eatingCreature.adjustHunger(-10) }
    }
}
