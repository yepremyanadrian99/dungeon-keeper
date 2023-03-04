package am.adrian.dungeonkeeper.game.controller

import am.adrian.dungeonkeeper.common.`object`.GameObject
import am.adrian.dungeonkeeper.common.constant.Direction
import am.adrian.dungeonkeeper.common.coords.Coords
import am.adrian.dungeonkeeper.game.GameMap
import am.adrian.dungeonkeeper.game.character.Goblin
import org.apache.logging.log4j.kotlin.Logging
import java.awt.event.KeyEvent

class GameKeyController(
    private val map: GameMap,
    private val cellSize: Int
) {

    companion object : Logging

    fun handleEvent(e: KeyEvent) {
        if (e.id != KeyEvent.KEY_TYPED) {
            return
        }
        val goblin = map.creatures[0] as Goblin
        val gameObject = creatureStandsOn(goblin.coords)
        logger.info("Goblin is on: " + gameObject.texture)

        if (e.keyChar == 'w') {
            goblin.walk(Direction.UP)
        } else if (e.keyChar == 's') {
            goblin.walk(Direction.DOWN)
        } else if (e.keyChar == 'a') {
            goblin.walk(Direction.LEFT)
        } else if (e.keyChar == 'd') {
            goblin.walk(Direction.RIGHT)
        }
    }

    private fun creatureStandsOn(coords: Coords): GameObject {
        return map.objectMap[coords.y / cellSize][coords.x / cellSize]
    }
}
