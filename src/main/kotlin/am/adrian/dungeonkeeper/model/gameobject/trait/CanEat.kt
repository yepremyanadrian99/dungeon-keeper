package am.adrian.dungeonkeeper.model.gameobject.trait

interface CanEat {
    companion object {
        const val MAX_HUNGER_LEVEL = 100
    }

    fun hungerLevel(): Int

    fun adjustHunger(level: Int)

    fun isHungry(): Boolean
}
