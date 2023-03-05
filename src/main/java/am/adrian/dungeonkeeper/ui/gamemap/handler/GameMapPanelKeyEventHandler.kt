package am.adrian.dungeonkeeper.ui.gamemap.handler

import am.adrian.dungeonkeeper.constant.Direction
import am.adrian.dungeonkeeper.game.GameMap
import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.character.Goblin
import am.adrian.dungeonkeeper.ui.PanelEventHandler
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.awt.event.KeyEvent

@Component
class GameMapPanelKeyEventHandler(
    private val map: GameMap,
    @Value("\${gameMapPanel.cellSize}") private val cellSize: Int
) : PanelEventHandler<KeyEvent> {

    companion object : Logging

    override fun handle(event: KeyEvent) {
        if (event.id != KeyEvent.KEY_TYPED) {
            return
        }

        val goblin = map.creatures[0] as Goblin
        val gameObject = creatureStandsOn(goblin.getCoords()) ?: return
        logger.info("Goblin is on: " + gameObject.getTexture())

        when (event.keyChar) {
            'w' -> goblin.walk(Direction.UP)
            's' -> goblin.walk(Direction.DOWN)
            'a' -> goblin.walk(Direction.LEFT)
            'd' -> goblin.walk(Direction.RIGHT)
        }
    }

    private fun creatureStandsOn(coords: Coords): GameObject? {
        return map.objectMap[coords.y() / cellSize][coords.x() / cellSize]
    }
}
