package am.adrian.dungeonkeeper.game

import am.adrian.dungeonkeeper.helper.CollisionChecker.objectsCollide
import am.adrian.dungeonkeeper.model.gameobject.Creature
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.Path
import am.adrian.dungeonkeeper.model.gameobject.tile.UnclaimedPath
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GameMap(
    @Value("\${gameMap.width}") val width: Int,
    @Value("\${gameMap.height}") val height: Int
) {

    val objectMap: Array<Array<GameObject?>> = Array(height) { arrayOfNulls(width) }
    val creatures: MutableList<Creature> = ArrayList()

    init {
        for (i in 0 until height) {
            for (j in 0 until width) {
                objectMap[i][j] = UnclaimedPath(j, i)
            }
        }
    }

    companion object : Logging

    fun addObject(gameObject: GameObject) {
        if (gameObject.coords == null) {
            logger.debug("Not adding object as its coords are null")
            return
        }

        logger.debug { "Adding object with x: ${gameObject.coords.x()} and y: ${gameObject.coords.y()}" }
        objectMap[gameObject.coords.y()][gameObject.coords.x()] = gameObject
    }

    fun addObjects(objectsToAdd: Collection<GameObject>) {
        objectsToAdd.forEach { gameObject: GameObject -> addObject(gameObject) }
    }

    fun removeObject(gameObject: GameObject) {
        if (gameObject.coords == null) {
            logger.debug("Not removing object as its coords are null")
            return
        }

        logger.debug { "Removing object with x: ${gameObject.coords.x()} and y: ${gameObject.coords.y()}" }
        removeObject(gameObject.coords.x(), gameObject.coords.y())
    }

    fun removeObject(x: Int, y: Int) {
        objectMap[y][x] = UnclaimedPath(x, y)
    }

    fun addCreature(creature: Creature) {
        val x: Int = creature.coords.x()
        val y: Int = creature.coords.y()
        val gameObject: GameObject = objectMap[y][x] ?: throw RuntimeException("Object was null at x: $x and y: $y")
        if (gameObject !is Path && objectsCollide(gameObject, creature)) {
            logger.debug("Not adding creature as its colliding with another object")
            return
        }

        logger.debug { "Adding creature with x: $x and y: $y" }
        creatures.add(creature)
    }

    fun addCreature(creaturesToAdd: Collection<Creature>) {
        creaturesToAdd.forEach { creature: Creature -> addCreature(creature) }
    }

    fun removeCreature(creature: Creature) {
        creatures.remove(creature)
    }
}