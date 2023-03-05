package am.adrian.dungeonkeeper.config

import am.adrian.dungeonkeeper.game.GameMap
import am.adrian.dungeonkeeper.helper.betweenPoints
import am.adrian.dungeonkeeper.model.coords.ImmutableCoords
import am.adrian.dungeonkeeper.model.gameobject.character.Goblin
import am.adrian.dungeonkeeper.model.gameobject.tile.wall.ImpenetrableWall
import am.adrian.dungeonkeeper.model.health.Health
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class GameConfig(
    private val map: GameMap,
    @Value("\${creatures.goblin.width}") private val goblinWidth: Int,
    @Value("\${creatures.goblin.height}") private val goblinHeight: Int,
    @Value("\${creatures.goblin.maxHealth}") private val goblinMaxHealth: Int
) {

    @PostConstruct
    open fun initObjects() {
        val width = map.width
        val height = map.height
        map.addObjects(
            betweenPoints(
                ImmutableCoords(0, 0),
                ImmutableCoords(width - 1, 0)
            ) { x: Int, y: Int -> ImpenetrableWall(x, y) }
        )
        map.addObjects(
            betweenPoints(
                ImmutableCoords(0, 0),
                ImmutableCoords(0, height - 1)
            ) { x: Int, y: Int -> ImpenetrableWall(x, y) }
        )
        map.addObjects(
            betweenPoints(
                0, height - 1,
                width - 1, height - 1
            ) { x: Int, y: Int -> ImpenetrableWall(x, y) }
        )
        map.addObjects(
            betweenPoints(
                width - 1, 0,
                width - 1, height - 1
            ) { x: Int, y: Int -> ImpenetrableWall(x, y) }
        )
    }

    @PostConstruct
    open fun initCharacters() {
        val goblin = Goblin(Health(goblinMaxHealth), goblinWidth, goblinHeight)
        goblin.getCoords().x(10)
        goblin.getCoords().y(4)
        map.addCreature(goblin)
    }
}
