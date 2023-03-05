package am.adrian.dungeonkeeper.model.gameobject.character

import am.adrian.dungeonkeeper.constant.Direction
import am.adrian.dungeonkeeper.model.coords.MutableCoords
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.trait.CanFear
import am.adrian.dungeonkeeper.model.gameobject.trait.CanLevel
import am.adrian.dungeonkeeper.model.gameobject.trait.CanSwim
import am.adrian.dungeonkeeper.model.gameobject.trait.CanWalk
import am.adrian.dungeonkeeper.model.gameobject.trait.HasHealth
import am.adrian.dungeonkeeper.model.gameobject.trait.HasMood
import am.adrian.dungeonkeeper.model.gameobject.trait.HasMood.Mood
import am.adrian.dungeonkeeper.model.health.Health

private const val TEXTURE = "goblin.png"

class Goblin(
    private val health: Health,
    private val width: Int,
    private val height: Int
) : Creature, HasHealth, CanLevel, CanWalk, CanSwim, HasMood, CanFear {

    private val coords: MutableCoords = MutableCoords()

    private var level: Int = 1
    private var mood = Mood.HAPPY

    override fun collides(gameObject: GameObject): Boolean = false

    override fun getCoords(): MutableCoords = coords

    override fun getTexture(): String = TEXTURE

    override fun getLevel(): Int = level

    override fun levelUp() {
        ++level
    }

    override fun getWidth(): Int = width

    override fun getHeight(): Int = height

    override fun walk(dir: Direction) = move(dir, walkDelta())

    override fun walkDelta(): Int = 5

    override fun swim(dir: Direction) = move(dir, swimDelta())

    override fun swimDelta(): Int = 1

    override fun getFearThreshold(): Int = getLevel() * 2

    override fun move(dir: Direction, delta: Int) {
        when (dir) {
            Direction.UP -> coords.incY(-delta)
            Direction.DOWN -> coords.incY(delta)
            Direction.LEFT -> coords.incX(-delta)
            Direction.RIGHT -> coords.incX(delta)
        }
    }

    override fun getHealth(): Health = health

    override fun getMood(): Mood = mood

    override fun setMood(mood: Mood) {
        this.mood = mood
    }
}
