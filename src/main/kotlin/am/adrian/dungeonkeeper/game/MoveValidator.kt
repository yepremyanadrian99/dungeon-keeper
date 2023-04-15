package am.adrian.dungeonkeeper.game

import am.adrian.dungeonkeeper.constant.Direction
import am.adrian.dungeonkeeper.model.coords.Coords
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.creature.Creature
import am.adrian.dungeonkeeper.model.gameobject.tile.path.Path
import am.adrian.dungeonkeeper.model.gameobject.tile.path.Water
import am.adrian.dungeonkeeper.model.gameobject.tile.wall.Wall
import am.adrian.dungeonkeeper.model.gameobject.trait.CanFly
import am.adrian.dungeonkeeper.model.gameobject.trait.CanSwim
import am.adrian.dungeonkeeper.model.gameobject.trait.CanWalk
import org.springframework.stereotype.Component

@Component
class MoveValidator(private val map: GameMap) {

    fun validateWalk(character: Creature, dir: Direction): Boolean =
        if (character !is CanWalk) {
            false
        } else {
            isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) is Path
        }

    fun validateSwim(character: Creature, dir: Direction): Boolean =
        if (character !is CanSwim) {
            false
        } else {
            isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) is Water
        }

    fun validateFly(character: Creature, dir: Direction): Boolean =
        if (character !is CanFly) {
            false
        } else {
            isMoveWithinMap(character, dir) && objectToMoveTo(character.getCoords(), dir) !is Wall
        }

    private fun isMoveWithinMap(character: Creature, dir: Direction): Boolean {
        return when (dir) {
            Direction.UP -> character.getCoords().y() > 0
            Direction.DOWN -> character.getCoords().y() < map.height - 1
            Direction.LEFT -> character.getCoords().x() > 0
            Direction.RIGHT -> character.getCoords().x() < map.width - 1
        }
    }

    private fun objectToMoveTo(characterCoords: Coords, dir: Direction): GameObject? {
        val x = characterCoords.x()
        val y = characterCoords.y()
        return when (dir) {
            Direction.UP -> map.objectMap[y - 1][x]
            Direction.DOWN -> map.objectMap[y + 1][x]
            Direction.LEFT -> map.objectMap[y][x - 1]
            Direction.RIGHT -> map.objectMap[y][x + 1]
        }
    }
}
