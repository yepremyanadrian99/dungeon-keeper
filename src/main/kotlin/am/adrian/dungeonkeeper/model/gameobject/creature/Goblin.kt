package am.adrian.dungeonkeeper.model.gameobject.creature

import am.adrian.dungeonkeeper.behavior.character.GoblinBehaviorTree
import am.adrian.dungeonkeeper.constant.Direction
import am.adrian.dungeonkeeper.helper.Players
import am.adrian.dungeonkeeper.model.coords.MutableCoords
import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.trait.*
import am.adrian.dungeonkeeper.model.gameobject.trait.HasMood.Mood
import am.adrian.dungeonkeeper.model.health.Health
import am.adrian.dungeonkeeper.model.player.Player
import kotlin.math.max
import kotlin.math.min

private const val TEXTURE = "goblin.png"

class Goblin(
    private val width: Int,
    private val height: Int,
    private val health: Health,
    private var hungerLevel: Int = 100,
    private val coords: MutableCoords = MutableCoords(),
    private var belongsTo: Player = Players.NEUTRAL_PLAYER,
    private var mood: Mood = Mood.HAPPY,
    private var level: Int = 1,
) : Creature, HasHealth, HasMood, HasBehavior, CanEat, CanWalk, CanSwim, CanLevel, CanFear {

    override fun collides(gameObject: GameObject): Boolean = false

    override fun getCoords(): MutableCoords = coords

    override fun getTexture(): String = TEXTURE

    override fun getLevel(): Int = level

    override fun levelUp() {
        ++level
    }

    override fun getWidth(): Int = width

    override fun getHeight(): Int = height

    override fun belongsTo(): Player = belongsTo

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

    override fun hungerLevel(): Int = hungerLevel

    override fun adjustHunger(level: Int) {
        hungerLevel = min(CanEat.MAX_HUNGER_LEVEL, max(0, hungerLevel + level))
    }

    override fun isHungry(): Boolean = hungerLevel < 20

    override fun getBehaviorTree() = GoblinBehaviorTree(this)
}
