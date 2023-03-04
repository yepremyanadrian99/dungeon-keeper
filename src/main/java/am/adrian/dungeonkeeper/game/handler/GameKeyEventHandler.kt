package am.adrian.dungeonkeeper.game.handler

import am.adrian.dungeonkeeper.common.`object`.GameObject
import am.adrian.dungeonkeeper.common.constant.Direction
import am.adrian.dungeonkeeper.common.coords.Coords
import am.adrian.dungeonkeeper.common.handler.GameEventHandler
import am.adrian.dungeonkeeper.game.GameMap
import am.adrian.dungeonkeeper.game.character.Goblin
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.event.KeyEvent

@Component
class GameKeyEventHandler(
    private val map: GameMap,
    @Value("\${window.cell.size}") private val cellSize: Int
) : GameEventHandler<KeyEvent> {

    companion object : Logging

    override fun handle(event: KeyEvent) {
        if (event.id != KeyEvent.KEY_TYPED) {
            return
        }
        val goblin = map.creatures[0] as Goblin
        val gameObject = creatureStandsOn(goblin.coords) ?: return
        logger.info("Goblin is on: " + gameObject.texture)

        when (event.keyChar) {
            'w' -> goblin.walk(Direction.UP)
            's' -> goblin.walk(Direction.DOWN)
            'a' -> goblin.walk(Direction.LEFT)
            'd' -> goblin.walk(Direction.RIGHT)
        }
    }

    private fun creatureStandsOn(coords: Coords): GameObject? {
        return map.objectMap[coords.y / cellSize][coords.x / cellSize]
    }
}
