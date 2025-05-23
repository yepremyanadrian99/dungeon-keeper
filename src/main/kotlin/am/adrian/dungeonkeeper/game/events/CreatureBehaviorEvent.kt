package am.adrian.dungeonkeeper.game.events

import am.adrian.dungeonkeeper.game.Game
import am.adrian.dungeonkeeper.model.gameobject.trait.HasBehavior
import am.adrian.dungeonkeeper.observer.Observable
import am.adrian.dungeonkeeper.observer.Observer
import am.adrian.dungeonkeeper.timer.GameTickTriggerer
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Component

@Component
class CreatureBehaviorEvent(
    private val game: Game,
    gameTickTriggerer: GameTickTriggerer,
) : Observer {

    companion object : Logging

    init {
        // Add this class as an observer of game ticks
        gameTickTriggerer.addObserver(this)
    }

    override fun updated(o: Observable) {
        logger.debug { "Executing behavior trees for all creatures." }

        // For each creature with a behavior tree, execute it on every tick
        game.creatures
            .filterIsInstance<HasBehavior>() // Filter creatures that implement HasBehavior
            .forEach { it.getBehaviorTree().execute() }
    }
}
