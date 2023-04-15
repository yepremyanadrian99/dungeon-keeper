package am.adrian.dungeonkeeper.model.gameobject.trait

const val MAX_HUNGER_LEVEL = 100

interface CanEat {

    fun isHungry(): Boolean

    fun eat()
}
